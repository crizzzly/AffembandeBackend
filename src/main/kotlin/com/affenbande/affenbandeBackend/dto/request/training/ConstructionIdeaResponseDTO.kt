package com.affenbande.affenbandeBackend.dto.request.training

class ConstructionIdeaResponseDTO(
    val name: String,
    val description: String?,
    val moveIds: List<Int?>?,
    val trainingDeviceIds: List<Int?>,
)