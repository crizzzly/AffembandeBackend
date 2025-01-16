package com.affenbande.affenbandeBackend.dto.response.training

class TrainingGoalResponseDTO(
    val id: Int,
    val name: String,
    val description: String,
    val moveIds: List<Int>?,
)