package com.affenbande.affenbandeBackend.dto.request

data class SubcategoryRequest(
    val name: String,
    val sports: List<String>?,
    val moves: List<String>?,
    val imagePathId: Int?,
)