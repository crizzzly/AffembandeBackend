package com.affenbande.affenbandeBackend.dto.request

data class SubcategoryRequestDTO(
    val name: String,
    val imagePathId: Int?,
    val sportIds: List<Int?>?,
    val moveIds: List<Int?>?,
    val trainingUnitIds: List<Int?>?,
    val trainingDeviceIds: List<Int?>?,
    val trainingPlanIds: List<Int?>?,
)