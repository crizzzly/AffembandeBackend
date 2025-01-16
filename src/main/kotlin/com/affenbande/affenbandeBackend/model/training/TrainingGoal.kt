package com.affenbande.affenbandeBackend.model.training

import com.affenbande.affenbandeBackend.dto.response.training.TrainingGoalResponseDTO
import com.affenbande.affenbandeBackend.model.Move
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table


@Entity
@Table(name="t_training_goals")
class TrainingGoal (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(unique = true)
    var name: String?,

    // TODO: description should be a list/set - create new table "descriptions"
    @Column
    var description: String? = null,

    @ManyToMany
    @JoinTable(
        name = "t_training_goal_moves",
        joinColumns = [JoinColumn(name = "fk_move_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_goal_id")]
    )
    var moves: Set<Move>? = mutableSetOf(),

//    @ManyToMany(cascade = [CascadeType.MERGE])
//    @JoinTable(
//        name = "t_training_goal_training_plans",
//        joinColumns = [JoinColumn(name = "fk_training_plan_id")],
//        inverseJoinColumns = [JoinColumn(name = "fk_training_goal_id")]
//    )
//    var trainingPlans: Set<TrainingPlan>? = mutableSetOf()
){
    constructor() : this(null, "", null, mutableSetOf())

    fun toResponseDTO(): TrainingGoalResponseDTO = TrainingGoalResponseDTO(
        id = id!!,
        name = name!!,
        description = description!!,
        moveIds = moves!!.map { it.id!! }
    )
}