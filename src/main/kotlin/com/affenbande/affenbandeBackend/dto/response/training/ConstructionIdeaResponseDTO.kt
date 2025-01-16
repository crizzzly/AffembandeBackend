package com.affenbande.affenbandeBackend.dto.response.training

class ConstructionIdeaResponseDTO(
    val id: Int?,
    val name: String,
    val description: String?,
    val moveIds: List<Int?>?,
    val trainingDeviceIds: List<Int?>,
    )