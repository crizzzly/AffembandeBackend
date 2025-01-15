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


@Entity
@Table(name="t_training_units")
class TrainingUnit(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(unique = true)
    var name: String,

    @Column(length = 1000)
    var description: String? = null,

    //TODO: here one should be able to choose an id multiple times
    @ManyToMany(cascade = [CascadeType.ALL])
    @JsonManagedReference
    @JoinTable(
        name = "t_training_unit_move",
        joinColumns = [JoinColumn(name = "fk_move_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_unit_id")]
    )
    var moves: List<Move>? = mutableListOf(),

    // TODO: Get corresponding

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "t_training_unit_sport",
        joinColumns = [JoinColumn(name = "fk_sport_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_unit_id")])
    var sports: List<Sport>? = mutableListOf(),

    @ManyToMany( cascade = [CascadeType.ALL])
    @JoinTable(
        name = "t_training_unit_subcategory",
        joinColumns = [JoinColumn(name = "fk_subcategory_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_unit_id")]
    )
    var subcategories: List<Subcategory>? = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "t_training_unit_training_device",
        joinColumns = [JoinColumn(name = "fk_training_device_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_unit_id")]
    )
    var trainingDevices: List<TrainingDevice>? = mutableListOf(),

    @ManyToMany(mappedBy = "trainingUnits")
    @JsonBackReference
    var trainingPlans: List<TrainingPlan>? = mutableListOf()






    )