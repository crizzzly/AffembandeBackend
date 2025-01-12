package com.affenbande.affenbandeBackend.dto

class SubcategoryResponseDTO(
    val id: Int?,
    val name: String,
    val imagePathId: Int?,
    val sportIds: List<Int?>?,
    val moveIds: List<Int?>?,
    val trainingSessionIds: List<Int?>?,
    val trainingPlanIds: List<Int?>?,
    val trainingDeviceIds: List<Int?>?,
    val constructionIdeaIds: List<Int?>?,
)
