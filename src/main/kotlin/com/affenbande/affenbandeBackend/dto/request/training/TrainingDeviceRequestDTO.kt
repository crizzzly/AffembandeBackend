package com.affenbande.affenbandeBackend.dto.request.training

import com.affenbande.affenbandeBackend.model.training.ConstructionIdea

data class TrainingDeviceRequestDTO(
    val name: String,
    val description: String,
    val moves: List<Int?>?,
    val sports: List<Int?>?,
    val subcategories: List<Int?>?,
    val constructionIdeaIds: List<Int?>?,
)
