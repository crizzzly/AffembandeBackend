package com.affenbande.affenbandeBackend.dao

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import java.util.*

/**
 * Base DAO class using JPA and Spring's EntityManager.
 */
abstract class BaseDao<E : Any>(private val entityType: Class<E>) {

    @PersistenceContext
    protected lateinit var entityManager: EntityManager

    /**
     * Insert the given entity into the table.
     */
    @Transactional
    open fun add(entity: E) {
        entityManager.persist(entity)
    }

    /**
     * Update the given entity in the table.
     */
    @Transactional
    open fun update(entity: E): E {
        return entityManager.merge(entity)
    }

    /**
     * Delete the given entity from the table.
     */
    @Transactional
    open fun delete(entity: E) {
        entityManager.remove(if (entityManager.contains(entity)) entity else entityManager.merge(entity))
    }

    @Transactional
    open fun deleteById(id: Int) {
        val entity = entityManager.find(entityType, id)
        if (entity != null) {
            entityManager.remove(entity)
        }
    }

    /**
     * Delete by ID.
     */
    @Transactional
    open fun deleteEntityAndRelations(
        entityId: Int,
        joinTableNames: List<String>,
        joinColumnNames: List<String>
    ) {
        if(joinTableNames.size != joinColumnNames.size) {
            throw IllegalArgumentException("joinTableNames and joinColumnNames must have the same size")
        }
        for (i in joinTableNames.size - 1 downTo 0) {
            val tableName = joinTableNames[i]
            val columnName = joinColumnNames[i]
            val queryString = "DELETE FROM $tableName WHERE $columnName = :entityId"
            entityManager.createNativeQuery(queryString).setParameter("entityId", entityId).executeUpdate()
        } // for
        deleteById(entityId)
    }

    /**
     * Find by ID.
     */
    @Transactional
    open fun findById(id: Any): Optional<E> {
        return Optional.ofNullable(entityManager.find(entityType, id))
    }

    @Transactional
    open fun findByIdOrNull(id: Any): E? {
        return entityManager.find(entityType, id)
    }


    /**
     * Find by Name
     */
    @Transactional
    open fun findByNameOrNull(name: String): E? {
        val query = entityManager.createQuery(
            "SELECT e FROM ${entityType.simpleName} e WHERE e.name = :name", entityType
        )
        query.setParameter("name", name)
        return query.resultList.firstOrNull()
    }

    /**
     * Find all entities of the type.
     */
    @Transactional
    open fun findAll(): List<E> {
        val query = entityManager.createQuery("from ${entityType.simpleName}", entityType)
        return query.resultList
    }

    /**
     * Count all entities of the type.
     */
    @Transactional
    open fun count(): Long {
        val query = entityManager.createQuery("select count(e) from ${entityType.simpleName} e", Long::class.javaObjectType)
        return query.singleResult
    }

    /**
     * Check if any entity matches a specific condition (using JPA's criteria query).
     */
    @Transactional
    open fun existsById(id: Any): Boolean {
        return findById(id).isPresent
    }

    @Transactional
    protected fun <T : Any> fetchRelatedEntities(
        ids: Set<Int?>?,
        dao: BaseDao<T>,
        notFoundMessage: String
    ): MutableSet<T> {
        return ids?.mapNotNull { id ->
            dao.findByIdOrNull(id!!) ?: throw NoSuchElementException("$notFoundMessage with ID $id not found")
        }?.toMutableSet() ?: mutableSetOf()
    }
}
