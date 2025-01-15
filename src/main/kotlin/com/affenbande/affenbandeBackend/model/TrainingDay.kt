package com.affenbande.affenbandeBackend.model

import com.fasterxml.jackson.annotation.JsonBackReference
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
@Table(name = "t_training_day")
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

    @ManyToMany(mappedBy = "trainingDays")
    @JsonBackReference
    @JoinTable(
        name = "training_plan_id",
        joinColumns = [JoinColumn(name = "training_plan_id")],
        inverseJoinColumns = [JoinColumn(name = "training_day_id")]
    )
    var trainingPlans: Int,

    // TODO: make them multiChoicable
    @Column
    var trainingUnits: List<TrainingUnit>


)