package com.affenbande.affenbandeBackend.dto.request.training

class TrainingDayRequestDTO(
    val name: String,
    val date: String,
    val startTime: String,
    val endTime: String,
    val trainingPlanIds: List<Int?>?,
    val trainingSessionIds: List<Int?>?,
)