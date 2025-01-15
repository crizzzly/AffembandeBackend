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
import java.time.LocalDateTime


@Entity
@Table(name = "t_training_session")
class TrainingSession(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(unique = true)
    var name: String,

    @Column
    var description: String,

    // TODO: get default duration from referenced trainingSets
    @Column
    var duration: Int,

    @Column
    var startTime: LocalDateTime?,



    // TODO: should contain at least one set, each set can be there multiple times
    @ManyToMany(mappedBy = "trainingSessions", cascade = [CascadeType.ALL])
    @JsonBackReference
    var trainingSets: List<TrainingSet> = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "t_training_session_sport",
        joinColumns = [JoinColumn(name = "fk_training_session_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_sport_id")]
    )
    var sports: List<Sport> = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(
            name = "t_training_session_subcategory",
            joinColumns = [JoinColumn(name = "fk_training_session_id")],
            inverseJoinColumns = [JoinColumn(name = "fk_subcategory_id")]
        )
    var subcategories: List<Subcategory> = mutableListOf(),




    )