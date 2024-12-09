package com.affenbande.affenbandeBackend.controller.helper


fun <T> loadRelatedEntitiesById(
    ids: List<String>, // List of related names  // TODO: check if list or string comes in
    dao: (String) -> T? // Function to fetch the related object by name, returning nullable T
                               ): List<T> {
    return ids.mapNotNull { entityId ->
        // TODO: this should at present only work if one element is preset
        val cleanedName = entityId.replace("[", "").replace("]", "")
        println("Loading related entity: $cleanedName")
        dao(cleanedName) // Return the entity if found, otherwise null
    }
}