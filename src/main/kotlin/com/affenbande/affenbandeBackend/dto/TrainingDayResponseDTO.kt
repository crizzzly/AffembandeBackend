package com.affenbande.affenbandeBackend.dto

class TrainingDayResponseDTO(
    val id: Int?,
    val name: String,
    val date: String,
    val startTime: String,
    val endTime: String,
    val trainingPlanIds: List<Int?>?,
//    val trainingSessionIds: List<Int?>?,

)