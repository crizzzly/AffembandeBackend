package com.affenbande.affenbandeBackend.services

import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.dto.request.MoveRequestDTO
import com.affenbande.affenbandeBackend.dto.response.MoveResponseDTO
import com.affenbande.affenbandeBackend.model.Move
import jakarta.transaction.Transactional
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
            sports = moveRequestDTO.sportIds.let { sportsIds ->
//                val sportSet = sportsIds?.toSet() ?: emptySet()
                sportsIds!!.map() {
                    sportDao.findByIdOrNull(it.toInt())
                        ?: throw NoSuchElementException("Sport with ID $it not found")
                }.toSet()
            }
//                sportSet.map {
//                    sportDao.findByIdOrNull(it.toInt())
//                        ?: throw NoSuchElementException("Sport with ID $it not found")
//

            subcategories = moveRequestDTO.subcategoryIds!!.map {
                subcategoryDao.findByIdOrNull(it.toInt())
                    ?: throw NoSuchElementException("Subcategory with ID $it not found")

            }.toSet()
                preMoves = moveRequestDTO.preMoveIds.let { moveIds ->
                    val moveSet = moveIds?.toSet() ?: emptySet()
                    moveSet.map {
                        moveDao.findByIdOrNull(it!!.toInt())
                            ?: throw NoSuchElementException("Move with ID $it not found")

                    }.toSet()

                }
                optPreMoves = moveRequestDTO.optPreMoveIds.let { moveIds ->
                    val moveSet = moveIds?.toSet() ?: emptySet()
                    moveSet.map {
                        moveDao.findByIdOrNull(it!!.toInt())
                            ?: throw NoSuchElementException("Move with ID $it not found")
                    }.toSet()
                }

                return try {
                    moveDao.add(move)
                    move.toResponseDTO()
                } catch (e: Exception) {
                    println("Failed to add move: ${move.name}")
                    throw Exception(
                        "Failed to add move: ${move.name}\n" +
                                "error: ${e.message}"
                    )
                }
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

            moveDao.updateMove(move, moveRequestDTO)
            return move.toResponseDTO()
        }

        @Transactional
        fun deleteMove(id: Int) {
            moveDao.deleteMoveById(id)
        }

    }
