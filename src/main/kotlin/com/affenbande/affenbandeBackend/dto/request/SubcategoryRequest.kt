package com.affenbande.affenbandeBackend.dto.request

data class SubcategoryRequest(
    val name: String,
    val sportIds: List<Int>?,
    val moveIds: List<Int>?,
    val imagePathId: Int?,
)