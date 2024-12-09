package com.affenbande.affenbandeBackend.services

import com.affenbande.affenbandeBackend.controller.helper.loadRelatedEntitiesById
import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.dto.SubcategoryRequestDTO
import com.affenbande.affenbandeBackend.dto.SubcategoryResponseDTO
import com.affenbande.affenbandeBackend.model.Subcategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class SubcategoryService {
    @Autowired
    lateinit var subcategoryDao: SubcategoryDao

    @Autowired
    lateinit var sportDao: SportDao

    @Autowired
    lateinit var moveDao: MoveDao

    @Autowired
    lateinit var imagePathDao: ImagePathDao

    fun addSubcategory(subcategoryRequestDTO: SubcategoryRequestDTO): SubcategoryResponseDTO {
        println("New Subcategory: $subcategoryRequestDTO")

        val subcategory = Subcategory()
        subcategory.name = subcategoryRequestDTO.name
        subcategory.sports = loadRelatedEntitiesById(subcategoryRequestDTO.sports!!, sportDao::findByNameOrNull)
        subcategory.image = imagePathDao.findById(subcategoryRequestDTO.imagePathId!!).orElse(null)

        if (subcategoryRequestDTO.moves!!.isNotEmpty()) {
            subcategory.moves = loadRelatedEntitiesById(subcategoryRequestDTO.moves, moveDao::findByNameOrNull)
        }

        subcategoryDao.add(subcategory)
        return subcategory.toResponseDTO()
    }

    fun updateSubcategory(id: Int, name: String): SubcategoryResponseDTO {
        val subcategory = subcategoryDao.findById(id).get()
        subcategory.name = name
        subcategoryDao.update(subcategory)
        return subcategory.toResponseDTO()
    }

    fun getSubcategoryById(id: Int): SubcategoryResponseDTO {
        return subcategoryDao.findById(id).get().toResponseDTO()
    }

    fun getAllSubcategories(): List<SubcategoryResponseDTO> {
        return subcategoryDao.findAll().map { it.toResponseDTO() }
    }

    fun deleteSubcategory(id: Int) {
        subcategoryDao.deleteById(id)
    }

    fun getSubcategoryByName(name: String): SubcategoryResponseDTO {
        return subcategoryDao.findByNameOrNull(name)!!.toResponseDTO()
    }

    fun getSubcategoriesBySportId(sportId: String): List<Optional<SubcategoryResponseDTO>> {
        val subcatIds = subcategoryDao.getSubcategoryIdsByRelatedSportId(sportId.toInt())

        return subcatIds!!.mapNotNull { id -> subcategoryDao.findById(id!!).map { it!!.toResponseDTO() } }
    }
}