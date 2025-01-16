package com.affenbande.affenbandeBackend.dto.request.training

class TrainingGoalRequestDTO(
    val name: String,
    val description: String,
    val moveIds: List<Int>?,
)