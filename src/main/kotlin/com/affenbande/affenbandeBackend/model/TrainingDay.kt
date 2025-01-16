package com.affenbande.affenbandeBackend.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
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
    var endTime: LocalDateTime,

    // TODO: make them multiChoicable
    @ManyToMany(cascade = [CascadeType.MERGE])
    @JsonBackReference
    @JoinTable(
        name = "t_training_day_training_sessions",
        joinColumns = [JoinColumn(name = "fk_training_day_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_session_id")]
    )
    var trainingSessions: Set<TrainingSession> = mutableSetOf(),

    @ManyToMany(cascade = [CascadeType.MERGE])
    @JsonManagedReference
    @JoinTable(
        name = "t_training_day_training_plans",
        joinColumns = [JoinColumn(name = "training_plan_id")],
        inverseJoinColumns = [JoinColumn(name = "training_day_id")]
    )
    var trainingPlans: Set<TrainingPlan> = mutableSetOf(),
)