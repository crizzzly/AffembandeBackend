package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.controller.helper.loadRelatedEntitiesById
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dto.SportRequest
import com.affenbande.affenbandeBackend.model.Sport
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/sports" )
@Tag(name = "Sports API", description = "Endpoints for managing sports")
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
            sport.subcategories = loadRelatedEntitiesById(request.subcategories, subcategoryDao::findByNameOrNull)
        }
        if (request.moves!!.isNotEmpty()) {
            sport.moves = loadRelatedEntitiesById(request.moves, moveDao::findByNameOrNull)
        }
        sportDao.add(sport)
        return ResponseEntity.ok(sport)
    }


    @GetMapping("/get-by-id/{id}")
    fun getSportById(@PathVariable("id") id: Int): ResponseEntity<Optional<Sport>> {
        val sport = sportDao.findById(id)
        return ResponseEntity.ok(sport)
    }

    @Operation(summary = "Get all sports", description = "Returns a list of all available sports.")
    @ApiResponses(value = [
      ApiResponse(responseCode = "200", description = "Successful operation", content = [
          (Content(mediaType = "application/json", schema = Schema(implementation = Array<SportRequest>::class)))]),
      ApiResponse(responseCode = "404", description = "No sports found", content = [Content()])
    ])
    @GetMapping("/get-all")
    fun getAllSports(): ResponseEntity<List<Sport>> {
        val sports = sportDao.findAll()

        println("Retrieved Sports: $sports")
        return ResponseEntity.ok(sports)
    }

    @GetMapping("/get-by-name/{name}")
    fun getSportByName(@PathVariable("name") name: String): ResponseEntity<Sport?> {
        val sport = sportDao.findByNameOrNull(name)
        return ResponseEntity(sport, sport?.let { HttpStatus.OK } ?: HttpStatus.NOT_FOUND)
    }
}
