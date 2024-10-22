package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.config.*
import com.affenbande.affenbandeBackend.controller.helper.loadRelatedEntitiesByName
import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.dto.MoveRequest
import com.affenbande.affenbandeBackend.model.Move
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/moves")
class MoveController {
    @Autowired
    lateinit var moveDao: MoveDao

    @Autowired
    lateinit var imagePathDao: ImagePathDao

    @Autowired
    lateinit var sportDao: SportDao

    @Autowired
    lateinit var subcategoryDao: SubcategoryDao

    companion object {
        const val OPERATION_ADD = "Add a new move."
    }
    @PostMapping("/add")
    @Operation(summary = OPERATION_ADD, description = OPERATION_ADD)
    @ApiResponses(value = [
        ApiResponse(responseCode = RESPONSE_CREATED_CODE, description = RESPONSE_CREATED_DESCRIPTION),
        ApiResponse(responseCode = RESPONSE_BAD_REQUEST_CODE, description = RESPONSE_BAD_REQUEST_DESCRIPTION),
        ApiResponse(responseCode = RESPONSE_INTERNAL_SERVER_ERROR_CODE, description = RESPONSE_INTERNAL_SERVER_ERROR_DESCRIPTION)
    ])
    fun addMove(
        @RequestBody request: MoveRequest,
    ): ResponseEntity<Move> {
        val move = Move()
        move.name = request.name
        move.description = request.description
        move.isCoreMove = request.isCoreMove ?: false
        move.level  = request.level ?: 0
        move.intensity = request.intensity ?: 0
        move.repetitions = request.repetitions ?: 0
        move.timePreparation = request.timePreparation ?: 0
        move.timeExercise = request.timeExercise ?: 0
        move.setFormula = request.setFormula

        println("Incomin Move Data")
        println("pre moves: ${request.preMoves}, opt pre moves ${request.optPreMoves}")

        if(request.preMoves != null) {
            move.preMoves = loadRelatedEntitiesByName(request.preMoves, moveDao::findByNameOrNull)
        }
        if(request.optPreMoves != null) {
            move.optPreMoves = loadRelatedEntitiesByName(request.optPreMoves, moveDao::findByNameOrNull)
        }
        if(request.subcategories != null) {
            move.subcategories = loadRelatedEntitiesByName(request.subcategories, subcategoryDao::findByNameOrNull)
        }
        if(request.sports != null) {
            move.sports = loadRelatedEntitiesByName(request.sports, sportDao::findByNameOrNull)
        }

        moveDao.add(move)
        return ResponseEntity.ok(move)
    }

    @GetMapping("/get-all")
    fun getAllMoves(): ResponseEntity<List<Move>> {
        val moves = moveDao.findAll()
        return ResponseEntity.ok(moves)
    }

    @GetMapping("/get-by-id")
    fun getMoveById(@RequestParam("id") id: Int): ResponseEntity<Optional<Move>> {
        val move = moveDao.findById(id)
        return ResponseEntity.ok(move)
    }

    @GetMapping("/get-by-name")
    fun getMoveByName(@RequestParam("name") name: String): ResponseEntity<Move> {
        val move = moveDao.findByNameOrNull(name)
        return ResponseEntity.ok(move)
    }
}