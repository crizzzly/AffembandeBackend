package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.model.ImageSrc
import com.affenbande.affenbandeBackend.model.Sport
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
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
            sport.imageSrc = handleImageInput(imageFile, filepath)
            var imageSrces = ImageSrc()
            imageSrces.name = sport.imageSrc?.name.toString()
            imageSrces.xs = sport.imageSrc?.xs
            imageSrces.s = sport.imageSrc?.s
            imageSrces.m = sport.imageSrc?.m
            imageSrces.l = sport.imageSrc?.l
            imageSrces.xl = sport.imageSrc?.xl
            imageSrcDao.add(imageSrces)

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
