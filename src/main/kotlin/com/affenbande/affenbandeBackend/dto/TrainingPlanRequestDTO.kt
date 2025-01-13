package com.affenbande.affenbandeBackend.dto

data class TrainingPlanRequestDTO(
    val name: String,
    val description: String?,
    val startDate: String,
    val endDate: String,
    val moveIds: List<String>?,
    val trainingDayIds: List<String>?,
    val sportIds: List<String>?,
    val subcategoryIds: List<String>?,

)