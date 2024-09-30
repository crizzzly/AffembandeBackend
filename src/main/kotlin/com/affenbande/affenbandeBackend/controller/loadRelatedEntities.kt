package com.affenbande.affenbandeBackend.controller


fun <T> loadRelatedEntitiesByName(
    names: List<String>, // List of related names
    dao: (String) -> T? // Function to fetch the related object by name, returning nullable T
): List<T> {
    return names.mapNotNull { name ->
        dao(name) // Return the entity if found, otherwise null
    }
}