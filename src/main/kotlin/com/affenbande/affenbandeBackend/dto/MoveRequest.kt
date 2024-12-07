package com.affenbande.affenbandeBackend.dto

data class MoveRequest(
    val name: String,
    val description: String?,
    val subcategories: List<String>?,
    val level: Int?,
    val isCoreMove: Boolean?,
    val intensity: Int?,
    val frequency: Int?,
    val timePreparation: Int?,
    val timeExercise: Int?,
    val formula: String?,
    val preMoves: List<String>?,
    val optPreMoves: List<String>?,
    val sports: List<String>?,
//    val imageFile: String? // You can handle file uploads separately if needed
)
