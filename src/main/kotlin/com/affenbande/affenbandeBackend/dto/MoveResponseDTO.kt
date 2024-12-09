package com.affenbande.affenbandeBackend.dto

class MoveResponseDTO(
    val id: Int?,
    val name: String,
    val description: String?,
    val subcategoryIds: List<Int?>?,
    val level: Int?,
    val isCoreMove: Boolean?,
    val intensity: Int?,
    val frequency: Int?,
    val timePreparation: Int?,
    val timeExercise: Int?,
    val formula: String?,
    val preMoveIds: List<Int?>?,
    val optPreMoveIds: List<Int?>?,
    val sportIds: List<Int?>?,
    val imagePathId: Int?,
                     )
