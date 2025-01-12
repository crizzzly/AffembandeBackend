package com.affenbande.affenbandeBackend.model;

import com.affenbande.affenbandeBackend.dto.TrainingDayResponseDTO
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.CascadeType
import jakarta.persistence.Column;
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import java.time.LocalDateTime


@Entity
@Table(name = "t_training_day")
class TrainingDay (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(unique = true)
    var name: String,

    @Column
    var date: LocalDateTime,

    @Column
    var startTime: LocalDateTime,

    @Column
    var endTime: LocalDateTime,

    @ManyToMany(mappedBy = "trainingDays", cascade = [CascadeType.ALL])
    @JsonBackReference
    var trainingPlans: List<TrainingPlan> = mutableListOf(),

//    @ManyToMany(mappedBy = "trainingDays", cascade = [CascadeType.ALL])
//    @JsonBackReference
//    var trainingSessions: List<TrainingSession> = mutableListOf(),
    ){
    // No-argument constructor
    constructor() : this(null, "", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), mutableListOf())

    fun toResponseDTO(): TrainingDayResponseDTO = TrainingDayResponseDTO(
        id = id,
        name = name,
        date = date.toString(),
        startTime = startTime.toString(),
        endTime = endTime.toString(),
        trainingPlanIds = trainingPlans.map { it.id },
//        trainingSessionIds = trainingSessions.map { it.id },
    )
}