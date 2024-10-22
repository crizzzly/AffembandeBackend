package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.controller.helper.loadRelatedEntitiesByName
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dto.SportRequest
import com.affenbande.affenbandeBackend.model.Sport
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/sports")
class SportController {
    @Autowired
    lateinit var sportDao: SportDao

    @Autowired
    lateinit var imagePathDao: com.affenbande.affenbandeBackend.dao.ImagePathDao

    @Autowired
    lateinit var subcategoryDao: com.affenbande.affenbandeBackend.dao.SubcategoryDao

    @Autowired
    lateinit var moveDao: com.affenbande.affenbandeBackend.dao.MoveDao


    @PostMapping("/add")
    fun addSport(
        @RequestBody request: SportRequest,
    ): ResponseEntity<out Any> {
        println("new sport: $request")
        val sport = Sport()
        sport.name = request.name
        sport.image = imagePathDao.findById(request.imagePathId!!).orElse(null)

        if (request.subcategories!!.isNotEmpty()) {
            sport.subcategories = loadRelatedEntitiesByName(request.subcategories, subcategoryDao::findByNameOrNull)
        }
        if (request.moves!!.isNotEmpty()) {
            sport.moves = loadRelatedEntitiesByName(request.moves, moveDao::findByNameOrNull)
        }
        sportDao.add(sport)
        return ResponseEntity.ok(sport)
    }


    @GetMapping("/get-by-id")
    fun getSportById(@RequestParam("id") id: Int): ResponseEntity<Optional<Sport>> {
        val sport = sportDao.findById(id)
        return ResponseEntity.ok(sport)
    }

    @GetMapping("/get-all")
    fun getAllSports(): ResponseEntity<List<Sport>> {
        val sports = sportDao.findAll()

        println("Retrieved Sports: $sports")
        return ResponseEntity.ok(sports)
    }

    @GetMapping("/get-by-name")
    fun getSportByName(@RequestParam("name") name: String): ResponseEntity<Sport?> {
        val sport = sportDao.findByNameOrNull(name)
        return ResponseEntity(sport, sport?.let { HttpStatus.OK } ?: HttpStatus.NOT_FOUND)
    }
}
