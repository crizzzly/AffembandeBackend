package com.affenbande.affenbandeBackend.dto.response.training

class TrainingSessionResponseDTO(
    val id: Int,
    val description: String,
    val name: String,
    val startTime: String,
    val duration: Int,
    val level: Int,
    val intensity: Int,
    val trainingSetIds: List<Int>,
    val preMoveIds: List<Int?>?,
    val sportIds: List<Int>?,
    val subcategoryIds: List<Int>?,
    val goalId: Int?,


    )