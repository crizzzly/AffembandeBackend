package com.affenbande.affenbandeBackend.services

import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.dto.SubcategoryRequestDTO
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
                    sportDao.findByIdOrNull(sportId)
                        ?: throw NoSuchElementException("Sport with ID $sportId not found")
                }
            } else {
                emptyList()
            }
        }.toSet()
        subcategory.moves = subcategoryRequestDTO.moveIds.let { moveIds ->
            if (moveIds!!.isNotEmpty()) {
                moveIds.map { moveId ->
                    moveDao.findByIdOrNull(moveId)
                        ?: throw NoSuchElementException("Move with ID $moveId not found")
                }
            } else {
                emptyList()
            }
        }.toSet()


        subcategoryDao.add(subcategory)
        return subcategory.toResponseDTO()
    }

    fun updateSubcategory(id: Int, subcat: SubcategoryRequestDTO): SubcategoryResponseDTO {
        val subcategory = subcategoryDao.findById(id).get()
        subcategory.name = subcat.name
        subcategory.image = imagePathDao.findByIdOrNull(subcat.imagePathId!!)
        subcategory.sports = subcat.sportIds!!.map { sportId ->
            sportDao.findByIdOrNull(sportId)
                ?: throw NoSuchElementException("Sport with ID $sportId not found")
        }.toSet()
        subcategory.moves = subcat.moveIds!!.map { moveId ->
            moveDao.findByIdOrNull(moveId)
                ?: throw NoSuchElementException("Move with ID $moveId not found")
            subcategoryDao.update(subcategory)
            return subcategory.toResponseDTO()
        }.toSet()

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
        subcategoryDao.deleteSubcategoryById(id)
    }

    fun getSubcategoryByName(name: String): SubcategoryResponseDTO {
        return subcategoryDao.findByNameOrNull(name)!!.toResponseDTO()
    }

    fun getSubcategoriesBySportId(sportId: String): List<Optional<SubcategoryResponseDTO>> {
        val subcatIds = subcategoryDao.getSubcategoryIdsByRelatedSportId(sportId.toInt())

        return subcatIds!!.mapNotNull { id -> subcategoryDao.findById(id!!).map { it!!.toResponseDTO() } }
    }
}