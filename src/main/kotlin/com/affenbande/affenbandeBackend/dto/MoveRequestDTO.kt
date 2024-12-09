package com.affenbande.affenbandeBackend.dto

data class MoveRequestDTO(
    val name: String,
    val description: String?,
    val level: Int?,
    val isCoreMove: Boolean?,
    val intensity: Int?,
    val frequency: Int?,
    val timePreparation: Int?,
    val timeExercise: Int?,
    val formula: String?,
    val sports: List<String>?,
    val subcategories: List<String>?,
    val preMoves: List<String>?,
    val optPreMoves: List<String>?,
//    val imageFile: String? // You can handle file uploads separately if needed
                         )
