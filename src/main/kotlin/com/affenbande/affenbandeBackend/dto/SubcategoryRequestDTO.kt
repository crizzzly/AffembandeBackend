package com.affenbande.affenbandeBackend.dto

data class SubcategoryRequestDTO(
    val name: String,
    val sports: List<String>?,
    val moves: List<String>?,
    val imagePathId: Int?,
                                )