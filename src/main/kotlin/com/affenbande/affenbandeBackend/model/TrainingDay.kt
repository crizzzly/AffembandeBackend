package com.affenbande.affenbandeBackend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
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

    @Column
    var trainingPlanId: Int,

    // TODO: make them multiChoicable
    @Column
    var trainingUnits: List<TrainingUnit>


)