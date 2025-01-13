package com.affenbande.affenbandeBackend.dto.request

data class SportIdsRequest(
    val sportId: Int
)

data class SportRequestDTO(
    val name: String,
    val subcategories: List<String>?,
    val moves: List<String>?,
    var imagePathId: Int?,
                          )