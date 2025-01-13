package com.affenbande.affenbandeBackend.model

import com.affenbande.affenbandeBackend.dto.TrainingDeviceResponseDTO
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
@Table(name="t_training_devices")
class TrainingDevice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(unique = true)
    var name: String,

    @Column(length = 1000)
    var description: String? = null,

    @ManyToMany(mappedBy = "training_devices", cascade = [CascadeType.MERGE])
    @JsonBackReference
    var moves: List<Move>? = mutableListOf(),

    @ManyToMany(mappedBy = "training_devices", cascade = [CascadeType.ALL])
    @JsonBackReference
    var sports: List<Sport>? = mutableListOf(),

    @ManyToMany(mappedBy = "training_devices", cascade = [CascadeType.MERGE])
    @JsonBackReference
    var subcategories: List<Subcategory>? = mutableListOf(),

    @ManyToMany(mappedBy = "training_devices", cascade = [CascadeType.MERGE])
    @JsonBackReference
    @JoinTable(
        name = "t_training_devices_training_units",
        joinColumns = [JoinColumn(name = "training_device_id")],
        inverseJoinColumns = [JoinColumn(name = "training_unit_id")]
    )
    var trainingUnits: List<TrainingUnit>? = mutableListOf(),
){
    constructor() : this(null, "", null, null, null, null, null)

    fun toResponseDTO(): TrainingDeviceResponseDTO = TrainingDeviceResponseDTO(
        id = id,
        name = name,
        description = description,
        moves = moves?.map { it.id },
        sports = sports?.map { it.id },
        subcategories = subcategories?.map { it.id },
        trainingUnits = trainingUnits?.map { it.id },
    )
}


