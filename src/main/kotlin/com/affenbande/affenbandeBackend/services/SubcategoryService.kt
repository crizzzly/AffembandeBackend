package com.affenbande.affenbandeBackend.services

import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.dto.request.SubcategoryRequestDTO
import com.affenbande.affenbandeBackend.dto.response.SubcategoryResponseDTO
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
        subcategory.image = imagePathDao.findByIdOrNull(subcategoryRequestDTO.imagePathId!!)

        subcategory.sports = subcategoryRequestDTO.sportIds.let { sportIds ->
            if (sportIds!!.isNotEmpty()) {
                sportIds.map { sportId ->
                    sportDao.findByIdOrNull(sportId!!.toInt())
                        ?: throw NoSuchElementException("Sport with ID $sportId not found")
                }
            } else {
                emptySet()
            }
        }.toSet()
        subcategory.moves = subcategoryRequestDTO.moveIds.let { moveIds ->
            if (moveIds!!.isNotEmpty()) {
                moveIds.map { moveId ->
                    moveDao.findByIdOrNull(moveId!!.toInt())
                        ?: throw NoSuchElementException("Move with ID $moveId not found")
                }
            } else {
                emptySet()
            }
        }.toSet()


        subcategoryDao.add(subcategory)
        return subcategory.toResponseDTO()
    }

    fun updateSubcategory(id: Int, newSubcat: SubcategoryRequestDTO): SubcategoryResponseDTO {
        val subcategory = subcategoryDao.findById(id).get()
        subcategory.name = newSubcat.name
        subcategoryDao.update(subcategory)
        return subcategory.toResponseDTO()
    }

    fun getSubcategoryById(id: Int): SubcategoryResponseDTO {
        return subcategoryDao.findById(id).get().toResponseDTO()
    }

    fun getAllSubcategories(): Set<SubcategoryResponseDTO> {
        return subcategoryDao.findAll().map { it.toResponseDTO() }.toSet()
    }

    fun deleteSubcategory(id: Int) {
        subcategoryDao.deleteById(id)
    }

    fun getSubcategoryByName(name: String): SubcategoryResponseDTO {
        return subcategoryDao.findByNameOrNull(name)!!.toResponseDTO()
    }

    fun getSubcategoriesBySportId(sportId: String): Set<Optional<SubcategoryResponseDTO>> {
        val subcatIds = subcategoryDao.getSubcategoryIdsByRelatedSportId(sportId.toInt())

        return subcatIds!!.mapNotNull { id -> subcategoryDao.findById(id!!).map { it.toResponseDTO() } }.toSet()
    }
}