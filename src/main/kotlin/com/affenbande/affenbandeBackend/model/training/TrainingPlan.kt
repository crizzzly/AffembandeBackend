package com.affenbande.affenbandeBackend.model.training

import com.affenbande.affenbandeBackend.dto.response.training.TrainingPlanResponseDTO
import com.affenbande.affenbandeBackend.helper.getCustomDateTimeFromLocalDateTime
import com.affenbande.affenbandeBackend.model.Move
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
import java.time.LocalDateTime


@Entity
@Table(name = "t_training_plans")
class TrainingPlan(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(unique = true)
    var name: String,

    @Column
    var description: String? = null,


    @Column(columnDefinition = "TIMESTAMP")
    var startDate: LocalDateTime,

    @Column(columnDefinition = "TIMESTAMP")
    var endDate: LocalDateTime,

    @Column
    var level: Int = 1,

    @Column
    var intensity: Int = 1,

    @ManyToMany(cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "t_training_plan_moves",
        joinColumns = [JoinColumn(name = "fk_training_plan_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_move_id")]
    )
    var preMoves: Set<Move>? = mutableSetOf(),




    // TODO: CHeck if One/ManyToMany
    @ManyToMany(cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "t_training_plan_training_days",
        joinColumns = [JoinColumn(name = "fk_training_day_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_plan_id")]
    )
    var trainingDays: Set<TrainingDay> = mutableSetOf(),

    @ManyToMany(cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "t_training_plan_training_goals",
        joinColumns = [JoinColumn(name = "fk_training_goal_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_plan_id")]
    )
    var goals: Set<TrainingGoal>? = mutableSetOf(),
    ){
    constructor() : this(null, "", null, LocalDateTime.now(), LocalDateTime.now(), 1, 1,  null, mutableSetOf())

    fun toResponseDTO(): TrainingPlanResponseDTO = TrainingPlanResponseDTO(
        id = id,
        name = name,
        description = description,
        startDate = getCustomDateTimeFromLocalDateTime(startDate),
        endDate = getCustomDateTimeFromLocalDateTime(endDate),
        level = level,
        intensity = intensity,
        preMoveIds = preMoves!!.map { it.id },
        trainingDayIds = trainingDays.map { it.id },
        goalIds = goals!!.map { it.id!! },
//        sportIds = sports!!.map { it.id },
//        subcategoryIds = subcategories!!.map { it.id },
    )


}