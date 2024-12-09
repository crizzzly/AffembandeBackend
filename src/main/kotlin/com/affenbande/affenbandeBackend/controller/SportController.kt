package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dto.SportRequestDTO
import com.affenbande.affenbandeBackend.dto.SportResponseDTO
import com.affenbande.affenbandeBackend.services.SportService
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
    lateinit var sportService: SportService


    @PostMapping("/add")
    fun addSport(
        @RequestBody request: SportRequestDTO,
    ): ResponseEntity<out Any> {
        return ResponseEntity.ok(sportService.addSport(request))
    }


    @GetMapping("/get-by-id/{id}")
    fun getSportById(@PathVariable("id") id: Int): ResponseEntity<SportResponseDTO> {
        return ResponseEntity.ok(sportService.getSportById(id))
    }


    @Operation(summary = "Get all sports", description = "Returns a list of all available sports.")
    @ApiResponses(value = [
      ApiResponse(responseCode = "200", description = "Successful operation", content = [
          (Content(mediaType = "application/json", schema = Schema(implementation = Array<SportRequestDTO>::class)))]
                 ),
      ApiResponse(responseCode = "404", description = "No sports found", content = [Content()])
    ])
    @GetMapping("/get-all")
    fun getAllSports(): ResponseEntity<List<SportResponseDTO>> {
        return ResponseEntity.ok(sportService.getAllSports())
    }


    @GetMapping("/get-by-name/{name}")
    fun getSportByName(@PathVariable("name") name: String): ResponseEntity<SportResponseDTO> {
        val sport = sportService.getSportByName(name)
        return ResponseEntity(sport, sport?.let { HttpStatus.OK } ?: HttpStatus.NOT_FOUND)
    }
}
