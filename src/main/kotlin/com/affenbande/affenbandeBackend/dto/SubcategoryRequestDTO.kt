package com.affenbande.affenbandeBackend.dto

data class SubcategoryRequestDTO(
    val name: String,
    val sportIds: List<Int>?,
    val moveIds: List<Int>?,
    val imagePathId: Int?,
)
