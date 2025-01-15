package com.affenbande.affenbandeBackend.dao

import com.affenbande.affenbandeBackend.model.Move
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

        deleteEntityAndRelationships(moveId, joinTableNames, joinColumnNames)
    }
}