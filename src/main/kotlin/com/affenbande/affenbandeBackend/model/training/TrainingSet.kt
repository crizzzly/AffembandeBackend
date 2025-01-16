package com.affenbande.affenbandeBackend.model.training

import com.affenbande.affenbandeBackend.dto.response.training.TrainingSetResponseDTO
import com.affenbande.affenbandeBackend.model.Move
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


@Entity
@Table(name = "t_training_sets")
class TrainingSet (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    // TODO: Should automatically take move.name as default value?
    @Column()
    var name: String?,


    // TODO: use Int or Duration? should be at least 1
    @Column
    var duration: Int,

    // TODO: should be at least 1
    @Column
    var repetitions: Int,


    @ManyToOne
    @JoinColumn(name = "move_id")
    var move: Move,



    @ManyToMany(cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "training_session_training_set",
        joinColumns = [JoinColumn(name = "fk_training_set_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_training_session_id")]
    )
    var trainingSessions: Set<TrainingSession> = mutableSetOf(),

    ){
    constructor() : this(null, "", 1, 1, Move())

    fun toResponseDTO(): TrainingSetResponseDTO = TrainingSetResponseDTO(
        id = id!!,
        name = name!!,
        moveId = move.id!!,
        repetitions = repetitions,
        duration = duration,
        trainingSessionIds = trainingSessions.map { it.id!! }
    )
}



