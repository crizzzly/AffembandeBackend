package com.affenbande.affenbandeBackend.model.training

import com.affenbande.affenbandeBackend.dto.response.training.TrainingSessionResponseDTO
import com.affenbande.affenbandeBackend.helper.getCustomDateTimeFromLocalDateTime
import com.affenbande.affenbandeBackend.model.Move
import com.affenbande.affenbandeBackend.model.Sport
import com.affenbande.affenbandeBackend.model.Subcategory
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime


@Entity
@Table(name = "t_training_sessions")
class TrainingSession(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(unique = true)
    var name: String,

    @Column
    var description: String,

    @Column
    var startTime: LocalDateTime?,


    // TODO: get default duration from referenced trainingSets
    @Column
    var duration: Int = 1,

    @Column
    var level: Int = 1,

    @Column
    var intensity: Int = 1,

    @ManyToMany(cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "t_training_session_move",
        joinColumns = [JoinColumn(name = "fk_training_session_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_move_id")]
    )
    var preMoves: Set<Move>? = mutableSetOf(),



    // TODO: should contain at least one set, each set can be there multiple times
    @ManyToMany(mappedBy = "trainingSessions", cascade = [CascadeType.MERGE])
    @JsonBackReference
    var trainingSets: Set<TrainingSet> = mutableSetOf(),

    @ManyToMany(cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "t_training_session_sport",
        joinColumns = [JoinColumn(name = "fk_training_session_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_sport_id")]
    )
    var sports: Set<Sport>? = mutableSetOf(),

    @ManyToMany(cascade = [CascadeType.MERGE])
        @JoinTable(
            name = "t_training_session_subcategory",
            joinColumns = [JoinColumn(name = "fk_training_session_id")],
            inverseJoinColumns = [JoinColumn(name = "fk_subcategory_id")]
        )
    var subcategories: Set<Subcategory>? = mutableSetOf(),


    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "goal_id")
    var goal: TrainingGoal? = null,
    ){
    constructor() : this(null, "", "", null, 1, 1, 1, mutableSetOf(), mutableSetOf(), mutableSetOf(), mutableSetOf(), null)

    fun toResponseDTO(): TrainingSessionResponseDTO = TrainingSessionResponseDTO(
        id = id!!,
        name = name,
        description = description,
        startTime = getCustomDateTimeFromLocalDateTime(startTime!!)!!,
        duration = duration,
        level = level,
        intensity = intensity,
        preMoveIds = preMoves!!.map { it.id },
        trainingSetIds = trainingSets.map { it.id!! },
        sportIds = sports!!.map { it.id!! },
        subcategoryIds = subcategories!!.map { it.id!! },
        goalId = goal!!.id
    )

}