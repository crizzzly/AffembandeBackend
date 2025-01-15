package com.affenbande.affenbandeBackend.dto.response

class TrainingPlanResponseDTO(
    val id: Int?,
    val name: String,
    val description: String?,
    val startDate: String,
    val endDate: String,
    val moveIds: List<Int?>?,
    val trainingDayIds: List<Int?>?,
    val sportIds: List<Int?>?,
    val subcategoryIds: List<Int?>?,


)