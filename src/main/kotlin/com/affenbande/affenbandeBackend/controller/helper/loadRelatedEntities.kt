package com.affenbande.affenbandeBackend.controller.helper


fun <T> loadRelatedEntitiesByName(
    names: List<String>, // List of related names
    dao: (String) -> T? // Function to fetch the related object by name, returning nullable T
): List<T> {
    return names.mapNotNull { name ->
        val cleanedName = name.replace("[", "").replace("]", "")
        println("Loading related entity: $cleanedName")
        dao(cleanedName) // Return the entity if found, otherwise null
    }
}