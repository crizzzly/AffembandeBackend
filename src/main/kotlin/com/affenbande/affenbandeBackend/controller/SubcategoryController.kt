package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.model.Move
import com.affenbande.affenbandeBackend.model.Sport
import com.affenbande.affenbandeBackend.model.Subcategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
class SubcategoryController {
    @Autowired
    lateinit var subcategoryDao: SubcategoryDao


    @GetMapping("/subcategories/addd")
    fun addSubcategory(
        @RequestParam("name", required = true   ) name: String,
        @RequestParam("imageSrc") imageSrc: String,
        @RequestParam("sports") sportIds: List<Int>,
        @RequestParam("moves") moveIds: List<Int>,
    ): Subcategory {
        val subcategory = Subcategory()


        subcategory.name = name
//        subcategory.imageSrc = imageSrc
//        subcategory.sportIds = sportIds
//        subcategory.moveIds = moveIds

        subcategoryDao.add(subcategory)
        return subcategory
    }

    @GetMapping("/subcategories/update")
    fun updateSubcategory(@RequestParam("id") id: kotlin.Int, @RequestParam("name") name: String) {
        val subcategory = subcategoryDao.findById(id).get()
        subcategory.name = name
        subcategoryDao.update(subcategory)
    }


    @GetMapping("/subcategories/get-all")
    fun getAllSubcategories(): List<Subcategory> {
        return subcategoryDao.findAll()
    }

    @GetMapping("/subcategories/get-by-id")
    fun getSubcategoryById(@RequestParam("id") id: kotlin.Int): Optional<Subcategory> {
        return subcategoryDao.findById(id)
    }

    @GetMapping("/subcategories/get-by-name")
    fun getSubcategoryByName(@RequestParam("name") name: String): Subcategory? {
        return subcategoryDao.findByNameOrNull(name)
    }

    @GetMapping("/subcategories/delete-by-id")
    fun deleteSubcategoryById(@RequestParam("id") id: kotlin.Int) {
        subcategoryDao.deleteById(id)
    }
}