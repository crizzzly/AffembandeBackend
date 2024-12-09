package com.affenbande.affenbandeBackend.services

import com.affenbande.affenbandeBackend.controller.helper.loadRelatedEntitiesById
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.dto.SportRequestDTO
import com.affenbande.affenbandeBackend.dto.SportResponseDTO
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
        sport.image = imagePathDao.findById(sportRequestDTO.imagePathId!!).orElse(null)

        if (sportRequestDTO.subcategories!!.isNotEmpty()) {
            sport.subcategories =
                loadRelatedEntitiesById(sportRequestDTO.subcategories, subcategoryDao::findByNameOrNull)
        }
        if (sportRequestDTO.moves!!.isNotEmpty()) {
            sport.moves = loadRelatedEntitiesById(sportRequestDTO.moves, moveDao::findByNameOrNull)
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

}
