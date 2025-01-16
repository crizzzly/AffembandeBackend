package com.affenbande.affenbandeBackend.model.training

import com.affenbande.affenbandeBackend.dto.response.training.TrainingDayResponseDTO
import com.affenbande.affenbandeBackend.helper.getCustomDateTimeFromLocalDateTime
import com.affenbande.affenbandeBackend.helper.getDateFromLocalDateTime
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
@Table(name = "t_training_days")
class TrainingDay (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column
    var date: LocalDateTime?,

    @Column(columnDefinition = "TIMESTAMP")
    var startTime: LocalDateTime?,

    @Column(columnDefinition = "TIMESTAMP")
    var endTime: LocalDateTime?,

    // TODO: make them multiChoicable
    @ManyToMany(cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "t_training_day_training_sessions",
        joinColumns = [JoinColumn(name = "fk_training_day_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_session_id")]
    )
    var trainingSessions: Set<TrainingSession> = mutableSetOf(),

    @ManyToMany(mappedBy = "trainingDays", cascade = [CascadeType.MERGE])
    var trainingPlans: Set<TrainingPlan> = mutableSetOf(),
) {
    constructor() : this(null, null, null, null, mutableSetOf(), mutableSetOf())

    fun toResponseDTO(): TrainingDayResponseDTO = TrainingDayResponseDTO(
        id = id,
        name = "",
        date = getDateFromLocalDateTime(date!!)!!,
        startTime = getCustomDateTimeFromLocalDateTime(startTime!!)!!,
        endTime = getCustomDateTimeFromLocalDateTime(endTime!!)!!,
        trainingPlanIds = trainingPlans.map { it.id },
        trainingSessionIds = trainingSessions.map { it.id }
    )

}