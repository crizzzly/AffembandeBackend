package com.affenbande.affenbandeBackend.dao

import com.affenbande.affenbandeBackend.model.Sport
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

@Component
class SportDao: BaseDao<Sport>(Sport::class.java){
    @Transactional
    fun deleteSportById(sportId: Int) {
        val joinTableNames = listOf("t_subcategory_sport", "t_move_sport")
        val joinColumnNames = listOf("fk_sport_id", "fk_sport_id")
        deleteEntityAndRelationships(sportId, joinTableNames, joinColumnNames)
    }
}
