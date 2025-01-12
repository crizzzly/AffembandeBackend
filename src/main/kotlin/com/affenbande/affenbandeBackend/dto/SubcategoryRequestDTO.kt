package com.affenbande.affenbandeBackend.dto

import com.affenbande.affenbandeBackend.model.Subcategory

data class SubcategoryRequestDTO(
    val name: String,
    val sports: List<String>?,
    val moves: List<String>?,
    val imagePathId: Int?,
)