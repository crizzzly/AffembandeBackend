package com.affenbande.affenbandeBackend.model

import jakarta.persistence.CascadeType
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
    var name: String,

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

    @ManyToMany(cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "t_training_goal_training_plans",
        joinColumns = [JoinColumn(name = "fk_training_plan_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_goal_id")]
    )
    var trainingPlans: Set<TrainingPlan>? = mutableSetOf()
)