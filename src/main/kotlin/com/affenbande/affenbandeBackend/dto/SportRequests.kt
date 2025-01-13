package com.affenbande.affenbandeBackend.dto

data class SportIdsRequest(
    val sportId: Int
)

data class SportRequestDTO(
    val name: String,
    val subcategories: List<String>?,
    var moves: List<String>?,
    var imagePathId: Int?,
    var trainingDevice: List<String>?,
    var trainingUnits: List<String>?,
    var trainingPlans: List<String>?,

                          )