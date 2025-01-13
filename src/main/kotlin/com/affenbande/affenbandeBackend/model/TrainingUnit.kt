package com.affenbande.affenbandeBackend.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
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

    //TODO: here one should be able to chose an id multiple times
    @ManyToMany(mappedBy = "training_units", cascade = [CascadeType.ALL])
    @JsonBackReference
    var moves: List<Move>? = mutableListOf(),

    @ManyToMany(mappedBy = "training_units", cascade = [CascadeType.ALL])
    @JsonBackReference
    var sports: List<Sport>? = mutableListOf(),

    @ManyToMany(mappedBy = "training_units", cascade = [CascadeType.ALL])
    @JsonBackReference
    var subcategories: List<Subcategory>? = mutableListOf(),

    @ManyToMany(mappedBy = "training_units", cascade = [CascadeType.ALL])
    @JsonBackReference
    var trainingDevices: List<TrainingDevice>? = mutableListOf(),






    )