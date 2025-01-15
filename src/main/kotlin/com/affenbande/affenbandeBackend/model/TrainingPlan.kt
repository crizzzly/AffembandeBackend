package com.affenbande.affenbandeBackend.model

import com.affenbande.affenbandeBackend.dto.response.TrainingPlanResponseDTO
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime


@Entity
@Table(name = "t_training_plan")
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

    @OneToMany(mappedBy = "training_plans")
    @JsonBackReference
    var trainingDays: List<TrainingDay>? = mutableListOf(),

    @ManyToMany(mappedBy = "training_plans", cascade = [CascadeType.ALL])
    @JsonBackReference
    var moves: List<Move>? = mutableListOf(),

    @ManyToMany(mappedBy = "training_plans", cascade = [CascadeType.ALL])
    @JsonBackReference
    var sports: List<Sport>? = mutableListOf(),

    @ManyToMany(mappedBy = "training_plans", cascade = [CascadeType.ALL])
    @JsonBackReference
    var subcategories: List<Subcategory>? = mutableListOf(),
    ){
    constructor() : this(null, "", null, LocalDateTime.now(), LocalDateTime.now(), mutableListOf(), null, null, null)

    fun toResponseDTO(): TrainingPlanResponseDTO = TrainingPlanResponseDTO(
        id = id,
        name = name,
        description = description,
        // TODO: format time
        startDate = startDate.toString(),
        endDate = endDate.toString(),
        moveIds = moves!!.map { it.id },
        trainingDayIds = trainingDays!!.map { it.id },
        sportIds = sports!!.map { it.id },
        subcategoryIds = subcategories!!.map { it.id },
    )


}