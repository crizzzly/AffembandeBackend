package com.affenbande.affenbandeBackend.dto

class ConstructionIdeaResponseDTO(
    val id: Int?,
    val name: String,
    val description: String,
    val trainingDeviceIds: List<Int?>?,
    val sportIds: List<Int?>?,
    val subcategoryIds: List<Int?>?,
    )