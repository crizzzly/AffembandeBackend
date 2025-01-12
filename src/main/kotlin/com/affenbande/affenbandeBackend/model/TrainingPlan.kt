package com.affenbande.affenbandeBackend.model

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
import jakarta.persistence.Table


@Entity
@Table(name = "t_training_plan")
class TrainingPlan(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(unique = true)
    var name: String,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "t_training_plan_training_day",
        joinColumns = [JoinColumn(name = "fk_training_day_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_plan_id")]
    )
    @JsonBackReference
    var trainingDays: List<TrainingDay> = mutableListOf(),


    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "t_training_plan_sport",
        joinColumns = [JoinColumn(name = "fk_sport_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_plan_id")]
    )
    var sports: List<Sport>? = mutableListOf(),


@ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "t_training_plan_subcategory",
        joinColumns = [JoinColumn(name = "fk_subcategory_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_plan_id")]
    )
    var subcategories: List<Subcategory> = mutableListOf(),




    )