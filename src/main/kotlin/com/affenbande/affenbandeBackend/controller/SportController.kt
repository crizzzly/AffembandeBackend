package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.model.ImagePath
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
    lateinit var imageSrcDao: com.affenbande.affenbandeBackend.dao.ImageSrcDao

    @PostMapping("/sports/add")
    fun addSport(
        @RequestParam("name") name: String,
        @RequestParam("image_file") imageFile: MultipartFile
    ): ResponseEntity<out Any> {
        val sport = Sport()
        sport.name = name
        if (!imageFile.isEmpty) {
//            val fileBytes = imageFile.bytes
            val filename = imageFile.originalFilename!!.split(".")[0]
            val filepath = "uploads/sports/$filename"
            val imagePaths = handleImageInput(imageFile, filepath)
//            val imageSrces = ImagePath()
//            imageSrces.name = sport.imagePath?.name.toString()
//            imageSrces.xs = sport.imagePath?.xs
//            imageSrces.s = sport.imagePath?.s
//            imageSrces.m = sport.imagePath?.m
//            imageSrces.l = sport.imagePath?.l
//            imageSrces.xl = sport.imagePath?.xl
            imageSrcDao.add(imagePaths)
            sport.imagePath = imagePaths
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
