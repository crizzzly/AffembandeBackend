package com.affenbande.affenbandeBackend.dto

import com.affenbande.affenbandeBackend.model.TrainingPlan

data class MoveRequestDTO(
    val name: String?,  // TODO: check if this can be nullable to remove issue in test
    val description: String?,
    val level: Int?,
    val link: String?,
    val isCoreMove: Boolean?,
    val intensity: Int?,
    val repetitions: Int?,
    val timePreparation: Int?,
    val timeExercise: Int?,
    val formula: String?,
    val sports: List<String>?,
    val subcategories: List<String>?,
    val preMoveIds: List<Int?>?,
    val optPreMoveIds: List<Int?>?,
    val trainingPlanIds: List<Int?>?,
    val trainingUnitIds: List<Int?>?,
    val trainingDeviceIds: List<Int?>?,
    val constructionIdeaIds: List<Int?>?,

//    val imageFile: String? // You can handle file uploads separately if needed
                         )
