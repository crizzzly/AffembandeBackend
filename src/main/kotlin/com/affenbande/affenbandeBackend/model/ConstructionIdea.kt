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

    @ManyToMany( cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "t_construction_idea_sport",
        joinColumns = [JoinColumn(name = "fk_sport_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_construction_idea_id")]
    )
    var sports: Set<Sport>? = mutableSetOf(),

    @ManyToMany(mappedBy = "constructionIdeas", cascade = [CascadeType.MERGE])
    @JsonBackReference
    var trainingDevices: Set<TrainingDevice>? = mutableSetOf(),
)

