package com.affenbande.affenbandeBackend.services

import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.dto.MoveRequestDTO
import com.affenbande.affenbandeBackend.dto.MoveResponseDTO
import com.affenbande.affenbandeBackend.model.Move
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MoveService {
    @Autowired
    lateinit var moveDao: MoveDao

    @Autowired
    lateinit var imagePathDao: ImagePathDao

    @Autowired
    lateinit var sportDao: SportDao

    @Autowired
    lateinit var subcategoryDao: SubcategoryDao

    fun addMove(moveRequestDTO: MoveRequestDTO): MoveResponseDTO {
        val move = Move()
        move.name = moveRequestDTO.name
        move.description = moveRequestDTO.description ?: ""
        move.isCoreMove = moveRequestDTO.isCoreMove ?: false
        move.level = moveRequestDTO.level ?: 0
        move.intensity = moveRequestDTO.intensity ?: 0
        move.repetitions = moveRequestDTO.frequency ?: 0
        move.timePreparation = moveRequestDTO.timePreparation ?: 0
        move.timeExercise = moveRequestDTO.timeExercise ?: 0
        move.formula = moveRequestDTO.formula

        move.apply {
            sports = moveRequestDTO.sports?.let {
//                val it = convertIdsToListOfInt(it)
                it.map { sportDao.findById(it).get() }
            }
            subcategories = moveRequestDTO.subcategories?.let { list ->
//                val it = convertIdsToListOfInt(it)
                list.map { subcategoryDao.findById(it).get() }
            }
            preMoves = moveRequestDTO.preMoves?.let { list ->
                list.map { moveDao.findById(it).get() }
            }
            optPreMoves = moveRequestDTO.optPreMoves?.let { list ->
                list.map { moveDao.findById(it).get() }
            }
        }

        return try {
            moveDao.add(move)
            move.toResponseDTO()
        } catch (e: Exception) {
            println("Failed to add move: ${move.name}")
            throw Exception("Failed to add move: ${move.name}")
        }
    }

    fun getAllMoves(): List<MoveResponseDTO> {
        return moveDao.findAll().map { it.toResponseDTO() }
    }

    fun getMoveById(id: Int): MoveResponseDTO {
        return moveDao.findById(id).get().toResponseDTO()
    }

    fun getMoveByName(name: String): MoveResponseDTO {
        return moveDao.findByNameOrNull(name)!!.toResponseDTO()
    }
}
