package com.affenbande.affenbandeBackend.dto.request

data class TrainingPlanRequestDTO(
    val name: String,
    val description: String?,
    val startDate: String,
    val endDate: String,
    val moveIds: List<Int?>?,
    val trainingDayIds: List<Int?>?,
    val sportIds: List<Int?>?,
    val subcategoryIds: List<Int?>?,

)