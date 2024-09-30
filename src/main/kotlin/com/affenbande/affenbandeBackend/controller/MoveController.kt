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
        @RequestParam("sports", required = false) sports: List<String>? = null,
        @RequestParam("image_file", required = false) imageFile: MultipartFile? = null
    ): Move {
        val move = Move()
        move.name = name
        move.description = description

        if(!imageFile!!.isEmpty) {
            val filename = imageFile.originalFilename!!.split(".")[0]
            val filepath = "uploads/subcategories/$filename"
            val imagePaths = handleImageInput(imageFile, filepath)
            imagePathDao.add(imagePaths)
            move.imagePath = imagePaths
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
    fun getMoveById(@RequestParam("id") id: kotlin.Int): Optional<Move> {
        val move = moveDao.findById(id)
        return move
    }

    @GetMapping("/moves/get-by-name")
    fun getMoveByName(@RequestParam("name") name: String): Move? {
        return moveDao.findByNameOrNull(name)
    }
}