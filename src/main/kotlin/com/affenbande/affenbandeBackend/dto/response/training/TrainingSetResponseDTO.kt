package com.affenbande.affenbandeBackend.dto.response.training

class TrainingSetResponseDTO(
    val id: Int,
    val name: String,
    val moveId: Int,
    val duration: Int?,
    val repetitions: Int?,
    val trainingSessionIds: List<Int>?,
)