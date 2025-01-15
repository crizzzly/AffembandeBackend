package com.affenbande.affenbandeBackend.dto.request

data class SportRequestDTO(
    val name: String,
    val subcategoryIds: List<Int?>?,
    var moveIds: List<Int?>?,
    var imagePathId: Int?,
    var trainingDeviceIds: List<Int?>?,
    var trainingUnitIds: List<Int?>?,
    var trainingPlanIds: List<Int?>?,

    )