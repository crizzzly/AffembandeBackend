package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.model.Sport
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class SportController {
    @Autowired
    lateinit var sportDao: SportDao

    @PostMapping("/sports/add")
    fun addSport(
        @RequestBody sport: Sport
//        @RequestParam(name = "name", required = true,) name: String,
//        @RequestParam(name = "imageSrc", required = false,) imageSrc: String,
//        @RequestParam(name = "subcategoryIds", required = false,) subcategoryIds: List<Int>? = null,
//        @RequestParam(name = "moveIds", required = false,) moveIds: List<Int>? = null,
    ): ResponseEntity<Sport> {
//        val sport = Sport(
//            name = name,
//            imageSrc = imageSrc,
//            subcategoryIds = subcategoryIds?.toList(),
//            moveIds = moveIds?.toList(),
//        )
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
