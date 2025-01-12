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

// 0-x moves
// 0-x sports
// 0-x subcats
// 0-x constructionIdeas

@Entity
@Table(name = "t_training_device")
class TrainingDevice (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(unique = true)
    var name: String,

    @Column
    var description: String,


    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "t_construction_idea_training_device",
        joinColumns = [JoinColumn(name = "fk_construction_idea_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_device_id")]
    )
    var constructionIdeas: List<ConstructionIdea> = mutableListOf(),


    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "t_training_device_sport",
        joinColumns = [JoinColumn(name = "fk_sport_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_device_id")]
    )
    var sports: List<Sport>? = mutableListOf(),


    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "t_training_device_subcategory",
        joinColumns = [JoinColumn(name = "fk_subcategory_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_device_id")]
    )
    var subcategories: List<Subcategory> = mutableListOf(),

    )