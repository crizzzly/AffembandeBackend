package com.affenbande.affenbandeBackend.dto

data class MoveRequest(
    val name: String,
    val description: String?,
    val subcategories: List<String>?,
    val level: Int?,
    val is_core_move: Boolean?,
    val intensity: Int?,
    val frequency: Int?,
    val time_preparation: Int?,
    val time_exercise: Int?,
    val formula: String?,
    val pre_moves: List<String>?,
    val opt_pre_moves: List<String>?,
    val sports: List<String>?,
//    val imageFile: String? // You can handle file uploads separately if needed
)
