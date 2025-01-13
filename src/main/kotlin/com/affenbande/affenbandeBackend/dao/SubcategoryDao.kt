package com.affenbande.affenbandeBackend.dao

import com.affenbande.affenbandeBackend.model.Subcategory
import jakarta.transaction.Transactional
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Component


@Component
class SubcategoryDao: BaseDao<Subcategory>(Subcategory::class.java) {
    fun getSubcategoryIdsByRelatedSportId(@Param("sportId") sportId: Int): MutableList<Any?>? {
    return  entityManager.createQuery(
        "SELECT s.id FROM Subcategory s JOIN s.sports sport WHERE sport.id = :sportId"
                                     )
            .setParameter("sportId", sportId)
            .resultList
    }

    @Transactional
    fun deleteSubcategoryAndRelatedEntitiesById(subcategoryId: Int) {
        val joinTableNames = listOf("t_subcategory_sport", "t_move_subcategory")
        val joinColumnNames = listOf("fk_subcategory_id", "fk_subcategory_id")
        deleteEntityAndRelations(subcategoryId, joinTableNames, joinColumnNames)
    }

}