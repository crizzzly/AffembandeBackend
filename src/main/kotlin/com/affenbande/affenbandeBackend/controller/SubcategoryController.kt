package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dto.SubcategoryRequestDTO
import com.affenbande.affenbandeBackend.dto.SubcategoryResponseDTO
import com.affenbande.affenbandeBackend.services.SubcategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/subcategories")
class SubcategoryController {

    @Autowired
    private lateinit var subcategoryService: SubcategoryService

    @PostMapping("/add")
    fun addSubcategory(
        @RequestBody request: SubcategoryRequestDTO,
                      ): ResponseEntity<SubcategoryResponseDTO> {

        return ResponseEntity.ok(subcategoryService.addSubcategory(request))
    }

    @PostMapping("/update")
    fun updateSubcategory(
        @RequestParam("id") id: Int,
        @RequestParam("name") name: String
                         ): ResponseEntity<SubcategoryResponseDTO> {
        return ResponseEntity.ok(subcategoryService.updateSubcategory(id, name))
    }


    @GetMapping("/get-all")
    fun getAllSubcategories(): ResponseEntity<List<SubcategoryResponseDTO>> {
        return ResponseEntity.ok(subcategoryService.getAllSubcategories())
    }

    @GetMapping("/get-by-id/{id}")
    fun getSubcategoryById(@PathVariable("id") id: Int): ResponseEntity<SubcategoryResponseDTO> {
        val subcat = subcategoryService.getSubcategoryById(id)
        return ResponseEntity(subcat, subcat.let { HttpStatus.OK } ?: HttpStatus.NOT_FOUND)
    }

    @GetMapping("/get-by-name/{name}")
    fun getSubcategoryByName(@PathVariable("name") name: String): ResponseEntity<SubcategoryResponseDTO> {
        val subcat = subcategoryService.getSubcategoryByName(name)
        return ResponseEntity(subcat, subcat?.let { HttpStatus.OK } ?: HttpStatus.NOT_FOUND)
    }

    @GetMapping("/get-by-sport/{sportId}")
    fun getSubcategoriesBySportId(@PathVariable("sportId") sportId: String): ResponseEntity<List<Optional<SubcategoryResponseDTO>>> {
        println("getSubcategoriesBySportId Request: $sportId")
        return ResponseEntity.ok(subcategoryService.getSubcategoriesBySportId(sportId))
    }

    @DeleteMapping("/delete-by-id/{id}")
    fun deleteSubcategoryById(@PathVariable("id") id: Int): ResponseEntity<Unit> {
        return ResponseEntity.ok(subcategoryService.deleteSubcategory(id))
    }
}