package com.affenbande.affenbandeBackend.dto.request.training

class TrainingSessionRequestDTO(
    val name: String,
    val description: String,
    val startTime: String,
    val duration: Int,
    val level: Int,
    val intensity: Int,
    val preMoveIds: List<Int?>?,
    val trainingSetIds: List<Int>,
    val sportIds: List<Int>?,
    val subcategoryIds: List<Int>?,
    val goalId: Int?,
)