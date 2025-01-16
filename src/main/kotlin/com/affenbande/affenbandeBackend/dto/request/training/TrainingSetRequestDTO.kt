package com.affenbande.affenbandeBackend.dto.request.training

class TrainingSetRequestDTO(
    val name: String,
    val duration: Int,
    val repetitions: Int,
    val moveId: Int,
    val trainingSessionIds: List<Int?>?,
)