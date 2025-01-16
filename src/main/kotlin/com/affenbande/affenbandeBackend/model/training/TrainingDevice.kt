package com.affenbande.affenbandeBackend.model.training

import com.affenbande.affenbandeBackend.model.Move
import com.affenbande.affenbandeBackend.model.Sport
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


    @ManyToMany(cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "t_training_devices_sports",
        joinColumns = [JoinColumn(name = "fk_sport_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_device_id")]
    )
    var sports: Set<Sport>? = mutableSetOf(),

    @ManyToMany(cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "t_training_devices_moves",
        joinColumns = [JoinColumn(name = "fk_move_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_device_id")]
    )
    var moves: Set<Move>? = mutableSetOf(),


    @ManyToMany(cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "training_device_construction_ideas",
        joinColumns = [JoinColumn(name = "fk_construction_idea_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_device_id")]
    )
    var constructionIdeas: Set<ConstructionIdea>? = mutableSetOf(),
){
    constructor() : this(null, "", null, null, null)
//
//    fun toResponseDTO(): TrainingDeviceResponseDTO = TrainingDeviceResponseDTO(
//        id = id,
//        name = name,
//        description = description,
//        moves = moves!!.map { it.id },
//        constructionIdeas = constructionIdeas!!.map { it.id },
//    )
}


