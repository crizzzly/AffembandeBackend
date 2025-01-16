package com.affenbande.affenbandeBackend.model.training

import com.affenbande.affenbandeBackend.dto.response.training.ConstructionIdeaResponseDTO
import com.affenbande.affenbandeBackend.model.Move
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
@Table(name = "t_construction_ideas")
class ConstructionIdea(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(unique = true)
    var name: String,

    @Column
    var description: String?,

    @ManyToMany(cascade = [CascadeType.MERGE])
    @JsonManagedReference
    @JoinTable(
        name = "t_construction_idea_move",
        joinColumns = [JoinColumn(name = "fk_move_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_construction_idea_id")]
    )
    var moves: Set<Move>? = mutableSetOf(),

    @ManyToMany(mappedBy = "constructionIdeas", cascade = [CascadeType.MERGE])
    @JsonManagedReference
    var trainingDevices: Set<TrainingDevice> = mutableSetOf(),
) {
    // No-argument constructor
    constructor() : this(null, "", null, null, mutableSetOf())

    fun toResponseDTO(): ConstructionIdeaResponseDTO = ConstructionIdeaResponseDTO(
        id = id,
        name = name,
        description = description!!,
        moveIds = moves!!.map { it.id },
        trainingDeviceIds = trainingDevices.map { it.id }
    )
}

