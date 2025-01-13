package com.affenbande.affenbandeBackend.dao

import com.affenbande.affenbandeBackend.dto.request.MoveRequestDTO
import com.affenbande.affenbandeBackend.model.Move
import com.affenbande.affenbandeBackend.model.Sport
import com.affenbande.affenbandeBackend.model.Subcategory
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class MoveDao: BaseDao<Move>(Move::class.java){
    @Autowired
    lateinit var sportDao: SportDao



    @Autowired
    lateinit var subcategoryDao: SubcategoryDao

    @Transactional
    fun deleteMoveById(moveId: Int) {
        val joinTableNames = listOf(
            "t_move_pre_moves",
            "t_move_pre_moves",
            "t_move_opt_pre_moves",
            "t_move_opt_pre_moves",
            "t_move_sport",
            "t_move_subcategory")

        val joinColumnNames = listOf(
            "fk_move_id",
            "fk_pre_move_id",
            "fk_move_id",
            "fk_opt_pre_move_id",
            "fk_move_id",
            "fk_move_id")

        deleteEntityAndRelations(moveId, joinTableNames, joinColumnNames)
    }

    @Transactional
    fun updateMove(move: Move, moveRequestDTO: MoveRequestDTO): Move {
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
        move.sports = fetchRelatedEntities(moveRequestDTO.sportIds!!.toSet(), sportDao, "Sport") as Set<Sport>?
        move.subcategories = fetchRelatedEntities(moveRequestDTO.subcategoryIds!!.toSet(), subcategoryDao, "Subcategory") as Set<Subcategory>?
        move.preMoves = fetchRelatedEntities(moveRequestDTO.preMoveIds!!.toSet(), this, "Move")
        move.optPreMoves = fetchRelatedEntities(moveRequestDTO.optPreMoveIds!!.toSet(), this, "Move")

        return update(move)
    }
}