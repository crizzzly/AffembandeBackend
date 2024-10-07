package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.model.Move
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
class MoveController {
    @Autowired
    lateinit var moveDao: MoveDao

    @Autowired
    lateinit var imagePathDao: ImagePathDao

    @Autowired
    lateinit var sportDao: SportDao

    @Autowired
    lateinit var subcategoryDao: SubcategoryDao

    @PostMapping("/moves/add")
    fun addMove(
        @RequestParam("name", required = true) name: String,
        @RequestParam("description", required = false) description: String,
        @RequestParam("subcategories", required = false) subcategories: List<String>? = null,
        @RequestParam("level", required = false) level: Int? = null,
        @RequestParam("is_core_move", required = false) isCoreMove: Boolean? = null,
        @RequestParam("intensity", required = false) intensity: Int? = null,
        @RequestParam("repetitions", required = false) repetitions: Int? = null,
        @RequestParam("time_preparation", required = false) timePreparation: Int? = null,
        @RequestParam("time_exercise", required = false) timeExercise: Int? = null,
        @RequestParam("set_formula", required = false) setFormula: String? = null,
        @RequestParam("pre_moves", required = false) preMoves: List<String>? = null,
        @RequestParam("opt_pre_moves", required = false) optPreMoves: List<String>? = null,
        @RequestParam("sports", required = false) sports: List<String>? = null,
        @RequestParam("image_file", required = false) imageFile: MultipartFile? = null
    ): Move {
        val move = Move()
        move.name = name
        move.description = description
        move.isCoreMove = isCoreMove
        move.level  = level
        move.intensity = intensity
        move.repetitions = repetitions
        move.timePreparation = timePreparation
        move.timeExercise = timeExercise
        move.setFormula = setFormula

        println("Incomin Move Data")
        println("pre moves: $preMoves, opt pre moves $optPreMoves")
        if(preMoves != null) {
            move.preMoves = loadRelatedEntitiesByName(preMoves, moveDao::findByNameOrNull)
        }
        if(optPreMoves != null) {
            move.optPreMoves = loadRelatedEntitiesByName(optPreMoves, moveDao::findByNameOrNull)
        }


        if(imageFile != null && !imageFile.isEmpty) {
            val path = ImageConstants.MOVE_PATH + imageFile.originalFilename
            val imagePaths = handleImageInput(imageFile, path)
            imagePathDao.add(imagePaths)
            move.image = imagePaths
        }

        if(subcategories != null) {
            move.subcategories = loadRelatedEntitiesByName(subcategories, subcategoryDao::findByNameOrNull)
        }
        if(sports != null) {
            move.sports = loadRelatedEntitiesByName(sports, sportDao::findByNameOrNull)
        }

        moveDao.add(move)
        return move
    }

    @GetMapping("/moves/get-all")
    fun getAllMoves(): List<Move> {
        return moveDao.findAll()
    }

    @GetMapping("/moves/get-by-id")
    fun getMoveById(@RequestParam("id") id: Int): Optional<Move> {
        val move = moveDao.findById(id)
        return move
    }

    @GetMapping("/moves/get-by-name")
    fun getMoveByName(@RequestParam("name") name: String): Move? {
        return moveDao.findByNameOrNull(name)
    }
}