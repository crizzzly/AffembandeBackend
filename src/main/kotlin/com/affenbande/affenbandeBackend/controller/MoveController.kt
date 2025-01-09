package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dto.MoveRequestDTO
import com.affenbande.affenbandeBackend.dto.MoveResponseDTO
import com.affenbande.affenbandeBackend.services.MoveService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/moves", produces = ["application/json,charset=utf8"])
class MoveController {
    @Autowired
    lateinit var moveService: MoveService


    companion object {
        const val OPERATION_ADD = "Add a new move."
    }
    @PostMapping("/add", produces = ["application/json,charset=utf8"])
    @Operation(summary = OPERATION_ADD, description = OPERATION_ADD)
    fun addMove(@RequestBody moveRequestDTO: MoveRequestDTO): ResponseEntity<Any> {
        print("Incomin Move Data \n$moveRequestDTO")

        return try {
            ResponseEntity.ok(moveService.addMove(moveRequestDTO))
        } catch (e: Exception) {
            ResponseEntity("An error occurred: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @GetMapping("/get-all", produces = ["application/json,charset=utf8"])
    fun getAllMoves(): ResponseEntity<List<MoveResponseDTO>> {
        return ResponseEntity.ok(moveService.getAllMoves())
    }


    @GetMapping("/get-by-id/{id}", produces = ["application/json,charset=utf8"])
    fun getMoveById(@PathVariable("id") id: Int): ResponseEntity<MoveResponseDTO> {
        return ResponseEntity.ok(moveService.getMoveById(id))
    }


    @GetMapping("/get-by-name/{name}", produces = ["application/json,charset=utf8"])
    fun getMoveByName(@PathVariable("name") name: String): ResponseEntity<MoveResponseDTO> {
        return ResponseEntity.ok(moveService.getMoveByName(name))
    }

    @DeleteMapping("/delete/{id}", produces = ["application/json,charset=utf8"])
    fun deleteMoveById(@PathVariable("id") id: Int): ResponseEntity<Unit> {
        return ResponseEntity.ok(moveService.deleteMove(id))
    }

    @PostMapping("/update/{id}", produces = ["application/json,charset=utf8"])
    fun updateMove(
        @PathVariable("id") id: Int,
        @RequestBody moveRequestDTO: MoveRequestDTO
    ): ResponseEntity<MoveResponseDTO> {
        return ResponseEntity.ok(moveService.updateMove(id, moveRequestDTO))
    }
}
