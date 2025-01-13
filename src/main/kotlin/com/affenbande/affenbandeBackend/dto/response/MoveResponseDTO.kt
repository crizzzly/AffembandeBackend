package com.affenbande.affenbandeBackend.dto.response

class MoveResponseDTO(
    val id: Int?,
    val name: String,
    val description: String?,
    val imagePathId: Int?,
    val level: Int?,
    val link: String?,
    val isCoreMove: Boolean?,
    val intensity: Int?,
    val repetitions: Int?,
    val timePreparation: Int?,
    val timeExercise: Int?,
    val formula: String?,
    val sportIds: List<Int?>?,
    val subcategoryIds: List<Int?>?,
    val preMoveIds: List<Int?>?,
    val optPreMoveIds: List<Int?>?,
                     )
