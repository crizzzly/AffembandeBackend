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
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import kotlin.time.Duration


@Entity
@Table(name = "t_training_device")
class TrainingSet (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    // TODO: Should automatically take move.name as default value?
    @Column()
    var name: String?,

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "t_move_training_set",
        joinColumns = [JoinColumn(name = "fk_move_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_set_id")]
    )
    var move: Move,

    @Column
    var repetitions: Int,

    // TODO: use Int or Duration?
    @Column
    var duration: Duration,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "training_session_training_set",
        joinColumns = [JoinColumn(name = "fk_training_set_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_session_id")]
    )
    var trainingSessions: List<TrainingSession> = mutableListOf(),

)



