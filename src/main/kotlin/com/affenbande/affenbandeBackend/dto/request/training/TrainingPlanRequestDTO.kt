package com.affenbande.affenbandeBackend.dto.request.training

data class TrainingPlanRequestDTO(
    val name: String,
    val description: String?,
    val startDate: String,
    val endDate: String?,
    val level: Int?,
    val intensity: Int?,
    val preMoveIds: List<Int?>?,
    val trainingDayIds: List<Int?>?,
    val goalIds: List<Int?>?,
    )