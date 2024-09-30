package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.model.Sport
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.*


@RestController
class SportController {
    @Autowired
    lateinit var sportDao: SportDao

    @Autowired
    lateinit var imagePathDao: com.affenbande.affenbandeBackend.dao.ImagePathDao

    @Autowired
    lateinit var subcategoryDao: com.affenbande.affenbandeBackend.dao.SubcategoryDao

    @Autowired
    lateinit var moveDao: com.affenbande.affenbandeBackend.dao.MoveDao

    @PostMapping("/sports/add")
    fun addSport(
        @RequestParam("name") name: String,
        @RequestParam("image_file") imageFile: MultipartFile,
        @RequestParam("subcategories", required = false) subcategories: List<String>? = null,
        @RequestParam("moves", required = false) moves: List<String>? = null,
    ): ResponseEntity<out Any> {
        val sport = Sport()
        sport.name = name
        if (!imageFile.isEmpty) {
            val filename = imageFile.originalFilename!!.split(".")[0]
            val filepath = "uploads/sports/$filename"
            val imagePaths = handleImageInput(imageFile, filepath)

            imagePathDao.add(imagePaths)
            sport.imagePath = imagePaths
        }

        if (subcategories != null) {
            sport.subcategories = loadRelatedEntitiesByName(subcategories, subcategoryDao::findByNameOrNull)
        }
        if (moves != null) {
            sport.moves = loadRelatedEntitiesByName(moves, moveDao::findByNameOrNull)
        }


        sportDao.add(sport)
        return ResponseEntity.ok(sport)
    }

    @GetMapping("/sports/get-by-id")
    fun getSportById(@RequestParam("id") id: Int): ResponseEntity<Optional<Sport>> {
        val sport = sportDao.findById(id)
//        return ResponseEntity.ok(sport.get())
        return ResponseEntity.ok(sport)
    }

    @GetMapping("/sports/get-all")
    fun getAllSports(): ResponseEntity<List<Sport>> {
        val sports = sportDao.findAll()

        println("Retrieved Sports: $sports")
        return ResponseEntity.ok(sports)
    }

    @GetMapping("/sports/get-by-name")
    fun getSportByName(@RequestParam("name") name: String): ResponseEntity<Sport?> {
        val sport = sportDao.findByNameOrNull(name.toString())
        return ResponseEntity(sport, sport?.let { HttpStatus.OK } ?: HttpStatus.NOT_FOUND)
    }
}
