package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.controller.helper.loadRelatedEntitiesByName
import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.dto.SubcategoryRequest
import com.affenbande.affenbandeBackend.model.Subcategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/subcategories")
class SubcategoryController {
    @Autowired
    lateinit var subcategoryDao: SubcategoryDao

    @Autowired
    lateinit var sportDao: SportDao

    @Autowired
    lateinit var moveDao: MoveDao

    @Autowired
    lateinit var imagePathDao: ImagePathDao

    @PostMapping("/add")
    fun addSubcategory(
        @RequestBody request: SubcategoryRequest,
    ): ResponseEntity<Subcategory> {
        println("New Subcategory: $request")

        val subcategory = Subcategory()
        subcategory.name = request.name
        subcategory.sports = loadRelatedEntitiesByName(request.sports!!, sportDao::findByNameOrNull)
        subcategory.image = imagePathDao.findById(request.imagePathId!!).orElse(null)

        if (request.moves!!.isNotEmpty()) {
            subcategory.moves = loadRelatedEntitiesByName(request.moves, moveDao::findByNameOrNull)
        }

        subcategoryDao.add(subcategory)
        return ResponseEntity.ok(subcategory)
    }

    @PostMapping("/update")
    fun updateSubcategory(@RequestParam("id") id: Int, @RequestParam("name") name: String): ResponseEntity.BodyBuilder {
        val subcategory = subcategoryDao.findById(id).get()
        subcategory.name = name
        subcategoryDao.update(subcategory)
        return ResponseEntity.ok()
    }


    @GetMapping("/get-all")
    fun getAllSubcategories(): ResponseEntity<List<Subcategory>> {
        val subcats = subcategoryDao.findAll()
        return ResponseEntity.ok(subcats)
    }

    @GetMapping("/get-by-id")
    fun getSubcategoryById(@RequestParam("id") id: Int): ResponseEntity<Optional<Subcategory>> {
        val subcat = subcategoryDao.findById(id)
        return ResponseEntity(subcat, subcat.let { HttpStatus.OK } ?: HttpStatus.NOT_FOUND)
    }

    @GetMapping("/get-by-name")
    fun getSubcategoryByName(@RequestParam("name") name: String): ResponseEntity<Subcategory> {
        val subcat = subcategoryDao.findByNameOrNull(name)
        return ResponseEntity(subcat, subcat?.let { HttpStatus.OK } ?: HttpStatus.NOT_FOUND)
    }

    @GetMapping("/get-by-sport/{sportId}")
    fun getSubcategoriesBySportId(@RequestParam sportId: String): ResponseEntity<List<Subcategory>> {
        println("getSubcategoriesBySportId Request: $sportId")
        val subcatIds = subcategoryDao.getSubcategoryIdsByRelatedSportId(sportId.toInt())
        return ResponseEntity.ok(subcatIds!!.mapNotNull { subcategoryDao.findById(it!!).orElse(null) })
    }

    @DeleteMapping("/delete-by-id")
    fun deleteSubcategoryById(@RequestParam("id") id: Int): ResponseEntity.BodyBuilder {
        subcategoryDao.deleteById(id)
        return ResponseEntity.ok()
    }
}