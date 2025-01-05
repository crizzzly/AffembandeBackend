package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dto.SubcategoryRequestDTO
import com.affenbande.affenbandeBackend.dto.SubcategoryResponseDTO
import com.affenbande.affenbandeBackend.model.Subcategory
import com.affenbande.affenbandeBackend.services.SubcategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.collections.mutableListOf


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


    @GetMapping("/get-all-by-ids/{ids}")
    fun getSubcategoryById(@PathVariable("ids") ids: List<Int>): ResponseEntity<MutableList<SubcategoryResponseDTO>> {
         val subcats = mutableListOf<SubcategoryResponseDTO>();
        ids.map{ id ->
            val subcat = subcategoryService.getSubcategoryById(id)
            subcats.add(subcat)
        }
        return ResponseEntity(subcats, subcats.let { HttpStatus.OK })
    }

    @GetMapping("/get-by-name/{name}")
    fun getSubcategoryByName(@PathVariable("name") name: String): ResponseEntity<SubcategoryResponseDTO> {
        val subcat = subcategoryService.getSubcategoryByName(name)
        // TODO: Exception handling
        return ResponseEntity(subcat, subcat.let { HttpStatus.OK })
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