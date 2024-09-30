package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.model.Subcategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.*


@RestController
class SubcategoryController {
    @Autowired
    lateinit var subcategoryDao: SubcategoryDao

    @Autowired
    lateinit var sportDao: SportDao

    @Autowired
    lateinit var moveDao: MoveDao

    @Autowired
    lateinit var imagePathDao: ImagePathDao

    @PostMapping("/subcategories/add")
    fun addSubcategory(
        @RequestParam("name", required = true   ) name: String,
        @RequestParam("sports") sports: List<String>,
        @RequestParam("moves") moves: List<String>?  ,
        @RequestParam("image_file", required = false) imageFile: MultipartFile?
    ): Subcategory {
        val subcategory = Subcategory()
        subcategory.name = name
        subcategory.sports = loadRelatedEntitiesByName(sports, sportDao::findByNameOrNull)

        if (!imageFile!!.isEmpty) {
            val filename = imageFile.originalFilename!!.split(".")[0]
            val filepath = "uploads/sports/$filename"
            val imagePaths = handleImageInput(imageFile, filepath)

            imagePathDao.add(imagePaths)
            subcategory.imagePath = imagePaths
        }

        if (moves != null) {
            subcategory.moves = loadRelatedEntitiesByName(moves, moveDao::findByNameOrNull)
        }

        subcategoryDao.add(subcategory)
        return subcategory
    }

    @GetMapping("/subcategories/update")
    fun updateSubcategory(@RequestParam("id") id: Int, @RequestParam("name") name: String) {
        val subcategory = subcategoryDao.findById(id).get()
        subcategory.name = name
        subcategoryDao.update(subcategory)
    }


    @GetMapping("/subcategories/get-all")
    fun getAllSubcategories(): List<Subcategory> {
        return subcategoryDao.findAll()
    }

    @GetMapping("/subcategories/get-by-id")
    fun getSubcategoryById(@RequestParam("id") id: Int): Optional<Subcategory> {
        return subcategoryDao.findById(id)
    }

    @GetMapping("/subcategories/get-by-name")
    fun getSubcategoryByName(@RequestParam("name") name: String): Subcategory? {
        return subcategoryDao.findByNameOrNull(name)
    }

    @GetMapping("/subcategories/delete-by-id")
    fun deleteSubcategoryById(@RequestParam("id") id: Int) {
        subcategoryDao.deleteById(id)
    }
}