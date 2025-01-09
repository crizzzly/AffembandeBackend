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
import kotlin.text.toIntOrNull

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
        move.name = moveRequestDTO.name.toString()
        move.description = moveRequestDTO.description ?: ""
        move.isCoreMove = moveRequestDTO.isCoreMove == true
        move.level = moveRequestDTO.level ?: 0
        move.link = moveRequestDTO.link ?: ""
        move.intensity = moveRequestDTO.intensity ?: 0
        move.repetitions = moveRequestDTO.repetitions ?: 0
        move.timePreparation = moveRequestDTO.timePreparation ?: 0
        move.timeExercise = moveRequestDTO.timeExercise ?: 0
        move.formula = moveRequestDTO.formula

        move.apply {
            sports = moveRequestDTO.sports?.let { sportsIds ->
                if (sportsIds.isNotEmpty()) {
                    sportsIds.map { sportId ->  // Iterate through the list
                        sportDao.findByIdOrNull(sportId.toIntOrNull() ?: 0) // Use ID or default to 0 if invalid format
                            ?: throw NoSuchElementException("Sport with ID $sportId not found") // Handle if not found in DB
                    }
                } else {
                    emptyList() // Or handle empty list as needed (e.g., set to null)
                }
            }
            subcategories = moveRequestDTO.subcategories?.let { subcategoryIds ->
                if (subcategoryIds.isNotEmpty()) {
                    subcategoryIds.map { subcategoryId ->
                        subcategoryDao.findByIdOrNull(subcategoryId.toIntOrNull() ?: 0)
                            ?: throw NoSuchElementException("Subcategory with ID $subcategoryId not found")
                    }
                } else {
                    emptyList()
                }
            }
            preMoves = moveRequestDTO.preMoveIds?.let { moveIds ->
                if (moveIds.isNotEmpty()) {
                    moveIds.map { moveId ->
                        moveDao.findByIdOrNull(moveId ?: 0)
                            ?: throw NoSuchElementException("Move with ID $moveId not found")
                    }
                } else {
                    emptyList()
                }
            }
            optPreMoves = moveRequestDTO.preMoveIds?.let { moveIds ->
                if (moveIds.isNotEmpty()) {
                    moveIds.map { moveId ->
                        moveDao.findByIdOrNull(moveId ?: 0)
                            ?: throw NoSuchElementException("Move with ID $moveId not found")
                    }
                } else {
                    emptyList()
                }
            }
        }

        return try {
            moveDao.add(move)
            move.toResponseDTO()
        } catch (e: Exception) {
            println("Failed to add move: ${move.name}")
            throw Exception("Failed to add move: ${move.name}\n" +
                    "error: ${e.message}")
        }
    }

    fun getAllMoves(): List<MoveResponseDTO> {
        return moveDao.findAll().map { it.toResponseDTO() }
    }

    fun getMoveById(id: Int): MoveResponseDTO {
        return moveDao.findByIdOrNull(id)?.toResponseDTO()
            ?: throw NoSuchElementException("Move with ID $id not found")
    }

    fun getMoveByName(name: String): MoveResponseDTO {
        return moveDao.findByNameOrNull(name)?.toResponseDTO()
            ?: throw NoSuchElementException("Move with name $name not found")
    }

    fun updateMove(id: Int, moveRequestDTO: MoveRequestDTO): MoveResponseDTO {
        val move = moveDao.findByIdOrNull(id)
            ?: throw NoSuchElementException("Move with ID $id not found")
        move.name = moveRequestDTO.name.toString()
        move.link = moveRequestDTO.link
        move.description = moveRequestDTO.description
        move.isCoreMove = moveRequestDTO.isCoreMove
        move.level = moveRequestDTO.level
        move.intensity = moveRequestDTO.intensity
        move.repetitions = moveRequestDTO.repetitions
        move.timePreparation = moveRequestDTO.timePreparation
        move.timeExercise = moveRequestDTO.timeExercise
        move.formula = moveRequestDTO.formula
        move.sports = moveRequestDTO.sports?.let { sportsIds ->
            if (sportsIds.isNotEmpty()) {
                sportsIds.map { sportId ->
                    sportDao.findByIdOrNull(
                        sportId.toIntOrNull()
                            ?: 0
                    )
                        ?: throw NoSuchElementException("Sport with ID $sportId not found")
                }.toMutableList()
            } else {
                mutableListOf()
            }
        }
        move.subcategories = moveRequestDTO.subcategories?.let { subcategoryIds ->
            if (subcategoryIds.isNotEmpty()) {
                subcategoryIds.map { subcategoryId ->
                    subcategoryDao.findByIdOrNull(
                        subcategoryId.toIntOrNull()
                            ?: 0
                    )
                        ?: throw NoSuchElementException("Subcategory with ID $subcategoryId not found")
                }.toMutableList()
            } else {
                mutableListOf()
            }
        }
        move.preMoves = moveRequestDTO.preMoveIds?.let { moveIds ->
            if (moveIds.isNotEmpty()) {
                moveIds.map { moveId ->
                    moveDao.findByIdOrNull(
                        moveId
                            ?: 0
                    )
                        ?: throw NoSuchElementException("Move with ID $moveId not found")
                }.toMutableList()
            } else {
                mutableListOf()
            }
        }
        move.optPreMoves = moveRequestDTO.optPreMoveIds?.let { moveIds ->
            if (moveIds.isNotEmpty()) {
                moveIds.map { moveId ->
                    moveDao.findByIdOrNull(
                        moveId
                            ?: 0
                    )
                        ?: throw NoSuchElementException("Move with ID $moveId not found")
                }.toMutableList()
            } else {
                mutableListOf()
            }
        }
        moveDao.update(move)
        return move.toResponseDTO()
    }
        fun deleteMove(id: Int) {
            moveDao.deleteById(id)
        }

}
