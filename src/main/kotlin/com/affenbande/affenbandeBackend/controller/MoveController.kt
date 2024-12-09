package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.config.*
import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.dto.MoveRequestDTO
import com.affenbande.affenbandeBackend.model.Move
import com.affenbande.affenbandeBackend.services.MoveService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
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

    @Autowired
    lateinit var moveService: MoveService


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
        @RequestBody moveRequestDTO: MoveRequestDTO,
               ): ResponseEntity<Any> {

        println("Incomin Move Data")
        println(moveRequestDTO)

        lateinit var returnVal: ResponseEntity<Any>
        try {
            returnVal = ResponseEntity.ok(moveService.addMove(moveRequestDTO))
        } catch (e: Exception) {
            println("Error: ${e.message}")
            returnVal = ResponseEntity("An error occurred: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
//          HttpStatus.INTERNAL_SERVER_ERROR
        }
        return returnVal
    }

    @GetMapping("/get-all")
    fun getAllMoves(): ResponseEntity<List<Move>> {
        val moves = moveDao.findAll()
        return ResponseEntity.ok(moves)
    }

    @GetMapping("/get-by-id/{id}")
    fun getMoveById(@PathVariable("id") id: Int): ResponseEntity<Optional<Move>> {
        val move = moveDao.findById(id)
        return ResponseEntity.ok(move)
    }

    @GetMapping("/get-by-name/{name}")
    fun getMoveByName(@PathVariable("name") name: String): ResponseEntity<Move> {
        val move = moveDao.findByNameOrNull(name)
        return ResponseEntity.ok(move)
    }
}
