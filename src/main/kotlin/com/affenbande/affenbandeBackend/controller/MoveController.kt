package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.dto.MoveRequest
import com.affenbande.affenbandeBackend.model.Move
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
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


    @PostMapping("/add")
    fun addMove(
        @RequestBody request: MoveRequest,
    ): Move {
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

//
//        if(request.imageFile != null && !request.imageFile.isEmpty) {
//            val path = ImageConstants.MOVE_PATH + request.imageFile.originalFilename
//            val imagePaths = handleImageInput(request.imageFile, path)
//            imagePathDao.add(imagePaths)
//            move.image = imagePaths
//        }

        if(request.subcategories != null) {
            move.subcategories = loadRelatedEntitiesByName(request.subcategories, subcategoryDao::findByNameOrNull)
        }
        if(request.sports != null) {
            move.sports = loadRelatedEntitiesByName(request.sports, sportDao::findByNameOrNull)
        }

        moveDao.add(move)
        return move
    }

    @GetMapping("/get-all")
    fun getAllMoves(): List<Move> {
        return moveDao.findAll()
    }

    @GetMapping("/get-by-id")
    fun getMoveById(@RequestParam("id") id: Int): Optional<Move> {
        val move = moveDao.findById(id)
        return move
    }

    @GetMapping("/get-by-name")
    fun getMoveByName(@RequestParam("name") name: String): Move? {
        return moveDao.findByNameOrNull(name)
    }
}