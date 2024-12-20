package com.affenbande.affenbandeBackend.controller.helper


fun <T> loadRelatedEntitiesById(
    ids: List<String>, // List of related names
    dao: (String) -> T? // Function to fetch the related object by name, returning nullable T
                               ): List<T> {
    return ids.map { entityId ->
        val cleanedName = entityId.replace("[", "").replace("]", "")
        println("Loading related entity: $cleanedName")
        dao(cleanedName) ?: throw // Return the entity if found, otherwise null
        NoSuchElementException("Related entity with ID $entityId not found")
    }
}