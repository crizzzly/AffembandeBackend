package com.affenbande.affenbandeBackend.dto

data class SportIdsRequest(
    val sportId: Int
)

data class SportRequest(
    val name: String,
    val subcategories : List<String>?,
    val moves : List<String>?,
    var imagePathId: Int?
)