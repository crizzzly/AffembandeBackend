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

// 0-x moves
// 0-x sports
// 0-x subcats
// 1-x trainingDevices
@Entity
@Table(name = "t_construction_idea")
class ConstructionIdea(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(unique = true)
    var name: String,

    @Column
    var description: String,

    @ManyToMany(mappedBy = "constructionIdeas", cascade = [CascadeType.ALL])
    @JsonBackReference
    var trainingDevices: List<TrainingDevice> = mutableListOf(),




    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "t_construction_idea_sport",
        joinColumns = [JoinColumn(name = "fk_sport_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_construction_idea_id")]
    )
    var sports: List<Sport>? = mutableListOf(),


    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "t_construction_idea_subcategory",
        joinColumns = [JoinColumn(name = "fk_subcategory_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_construction_idea_id")]
    )
    var subcategories: List<Subcategory> = mutableListOf(),

    )