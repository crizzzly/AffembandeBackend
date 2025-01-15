package com.affenbande.affenbandeBackend.services

import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.dto.request.SportRequestDTO
import com.affenbande.affenbandeBackend.dto.response.SportResponseDTO
import com.affenbande.affenbandeBackend.model.Sport
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class SportService {
    @Autowired
    lateinit var sportDao: SportDao

    @Autowired
    lateinit var imagePathDao: com.affenbande.affenbandeBackend.dao.ImagePathDao

    @Autowired
    lateinit var subcategoryDao: SubcategoryDao

    @Autowired
    lateinit var moveDao: MoveDao

    fun addSport(sportRequestDTO: SportRequestDTO): SportResponseDTO {
        println("new sport: $sportRequestDTO")
        val sport = Sport()
        sport.name = sportRequestDTO.name
        try {
        sportRequestDTO.imagePathId?.let {
            sport.image = imagePathDao.findByIdOrNull(it)
                ?: throw NoSuchElementException("No image found for this id.")
            }
        } catch (e: Exception) {
            // Handle the error (log, re-throw, etc.)
            println("Error retrieving ImagePath in SportService.addSport: ${e.message}")
            throw e // Re-throw if necessary
        }

        try {
            if (!sportRequestDTO.subcategoryIds.isNullOrEmpty()) {
                sport.subcategories = sportRequestDTO.subcategoryIds.let { subcategoryIds ->
                    if (subcategoryIds.isNotEmpty()) {
                        subcategoryIds.map { subcategoryId ->
                            subcategoryDao.findByIdOrNull(subcategoryId)
                                ?: throw NoSuchElementException("Subcategory with ID $subcategoryId not found")
                        }
                    } else {
                        emptyList()
                    }
                }.toSet()
            }
            if (!sportRequestDTO.moveIds.isNullOrEmpty()) {
                sport.moves = sportRequestDTO.moveIds.let { moveIds ->
                    if (moveIds.isNotEmpty() ) {
                        moveIds.map { moveId ->
                            moveDao.findByIdOrNull(moveId)
                                ?: throw NoSuchElementException("Move with ID $moveId not found")
                        }
                    } else {
                        emptyList()
                    }
                }.toSet()
            }
        }catch (e: Exception) {
            // Handle the error
            println("Error retrieving Subcategories or Moves in SportService.addSport: ${e.message}")
            throw e
        }
        sportDao.add(sport)
        return sport.toResponseDTO()
    }


    fun getSportById(id: Int): SportResponseDTO {
        return sportDao.findById(id).get().toResponseDTO()
    }

    fun getSportByName(name: String): SportResponseDTO {
        return sportDao.findByNameOrNull(name)!!.toResponseDTO()
    }

    fun getAllSports(): List<SportResponseDTO> {
        return sportDao.findAll().map { it.toResponseDTO() }
    }

    fun updateSport(id: Int, sportRequestDTO: SportRequestDTO): SportResponseDTO {
        val sport = sportDao.findById(id).get()
        sport.name = sportRequestDTO.name
        sport.image = imagePathDao.findByIdOrNull(sportRequestDTO.imagePathId!!)
        sport.subcategories = sportRequestDTO.subcategoryIds!!.map { subcategoryId ->
            subcategoryDao.findByIdOrNull(subcategoryId)
                ?: throw NoSuchElementException("Subcategory with ID $subcategoryId not found")
        }.toSet()
        sport.moves = sportRequestDTO.moveIds!!.map { moveId ->
            moveDao.findByIdOrNull(moveId)
                ?: throw NoSuchElementException("Move with ID $moveId not found")
        }.toSet()
        val updatedSport = sportDao.update(sport)
        return updatedSport.toResponseDTO()
    }

    fun deleteSportById(id: Int) {
        sportDao.deleteSportById(id)
    }

}
