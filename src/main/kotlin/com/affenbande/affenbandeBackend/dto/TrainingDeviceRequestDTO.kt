package com.affenbande.affenbandeBackend.dto

data class TrainingDeviceRequestDTO(
    val name: String,
    val description: String,
    val moves: List<Int>?,
    val sports: List<Int>?,
    val subcategories: List<Int>?,
    val trainingUnits: List<Int>?,
)
