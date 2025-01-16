package com.affenbande.affenbandeBackend.dto.response

class SportResponseDTO(
    val id: Int?,
    val name: String,
    val imagePathId: Int?,
    val subcategoryIds: List<Int?>?,
    val moveIds: List<Int?>?,
//    val trainingSessionIds: List<Int?>?,
    val trainingDeviceIds: List<Int?>?,
//    val trainingPlanIds: List<Int?>?
    )