package com.affenbande.affenbandeBackend.dao

import com.affenbande.affenbandeBackend.model.Subcategory
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Component


@Component
class SubcategoryDao: BaseDao<Subcategory>(Subcategory::class.java) {
    fun getSubcategoriesByRelatedSportId(@Param("sportId") sportId: Int): MutableList<Any?>? {
    return  entityManager.createQuery(
        "SELECT * FROM Subcategory s JOIN s.sports sport WHERE sport.id = :sportId")
            .setParameter("sportId", sportId)
            .resultList
    }
}