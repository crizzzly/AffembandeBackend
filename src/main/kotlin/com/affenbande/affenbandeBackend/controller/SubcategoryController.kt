package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.dto.SportIdsRequest
import com.affenbande.affenbandeBackend.dto.SubcategoryRequest
import com.affenbande.affenbandeBackend.model.Subcategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
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
    ): Subcategory {
        println("New Subcategory: $request")

        val subcategory = Subcategory()
        subcategory.name = request.name
        subcategory.sports = loadRelatedEntitiesByName(request.sports!!, sportDao::findByNameOrNull)
        subcategory.image = imagePathDao.findById(request.imagePathId!!).orElse(null)

        if (request.moves!!.isNotEmpty()) {
            subcategory.moves = loadRelatedEntitiesByName(request.moves, moveDao::findByNameOrNull)
        }

        subcategoryDao.add(subcategory)
        return subcategory
    }

    @GetMapping("/update")
    fun updateSubcategory(@RequestParam("id") id: Int, @RequestParam("name") name: String) {
        val subcategory = subcategoryDao.findById(id).get()
        subcategory.name = name
        subcategoryDao.update(subcategory)
    }


    @GetMapping("/get-all")
    fun getAllSubcategories(): List<Subcategory> {
        return subcategoryDao.findAll()
    }

    @GetMapping("/get-by-id")
    fun getSubcategoryById(@RequestParam("id") id: Int): Optional<Subcategory> {
        return subcategoryDao.findById(id)
    }

    @GetMapping("/get-by-name")
    fun getSubcategoryByName(@RequestParam("name") name: String): Subcategory? {
        return subcategoryDao.findByNameOrNull(name)
    }

    @PostMapping("/get-by-sport")
    fun getSubcategoriesBySportId(@RequestBody request: SportIdsRequest): List<Subcategory> {
        val sportId = request.sportId
        val subcatIds = subcategoryDao.getSubcategoryIdsByRelatedSportId(sportId)
        return subcatIds!!.mapNotNull { subcategoryDao.findById(it!!).orElse(null) }
    }

    @GetMapping("/delete-by-id")
    fun deleteSubcategoryById(@RequestParam("id") id: Int) {
        subcategoryDao.deleteById(id)
    }
}