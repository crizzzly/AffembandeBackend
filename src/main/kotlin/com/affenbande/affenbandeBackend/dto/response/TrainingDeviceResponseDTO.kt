package com.affenbande.affenbandeBackend.dto.response

class TrainingDeviceResponseDTO(
    val id: Int?,
    val name: String,
    val description: String?,
    val moves: List<Int?>?,
    val sports: List<Int?>?,
    val subcategories: List<Int?>?,
    val constructionIdeas: List<Int?>?,
) {
}