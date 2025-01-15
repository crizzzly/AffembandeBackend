package com.affenbande.affenbandeBackend.dto.request

data class SportIdsRequest(
    val sportId: Int
)

data class SportRequestDTO(
    val name: String,
    val subcategoryIds: List<Int>?,
    val moveIds: List<Int>?,
    var imagePathId: Int?,
                          )