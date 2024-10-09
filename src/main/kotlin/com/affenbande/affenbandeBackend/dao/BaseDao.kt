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

    /**
     * Delete by ID.
     */
    @Transactional
    open fun deleteById(id: Any) {
        val entity = entityManager.find(entityType, id)
        if (entity != null) {
            entityManager.remove(entity)
        }
    }

    /**
     * Find by ID.
     */
    open fun findById(id: Any): Optional<E> {
        return Optional.ofNullable(entityManager.find(entityType, id))
    }

//    /**
//     * Find by ID.
//     */
//    open fun findAllById(id: Any): Optional<E> {
//        return Optional.ofNullable(entityManager.find(entityType, id))
//    }

    /**
     * Find by Name
     */
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
    open fun findAll(): List<E> {
        val query = entityManager.createQuery("from ${entityType.simpleName}", entityType)
        return query.resultList
    }

    /**
     * Count all entities of the type.
     */
    open fun count(): Long {
        val query = entityManager.createQuery("select count(e) from ${entityType.simpleName} e", Long::class.javaObjectType)
        return query.singleResult
    }

    /**
     * Check if any entity matches a specific condition (using JPA's criteria query).
     */
    open fun existsById(id: Any): Boolean {
        return findById(id).isPresent
    }
}
