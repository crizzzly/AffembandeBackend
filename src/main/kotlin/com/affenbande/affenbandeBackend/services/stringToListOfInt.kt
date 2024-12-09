package com.affenbande.affenbandeBackend.services

fun convertIdsToListOfInt(
    stringIds: String, // List of related names  // TODO: check if list or string comes in
                         ): List<Int> {
    val cleanedIds = stringIds.replace("[", "").replace("]", "")

    return cleanedIds.split(",").map { it.toInt() }
}
