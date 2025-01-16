package com.affenbande.affenbandeBackend.model

import com.affenbande.affenbandeBackend.dto.response.SportResponseDTO
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "t_sports")
class Sport(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(unique = true)
    var name: String,

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "image_path_id")
    var image: ImagePath? = null ,

    @ManyToMany(mappedBy = "sports",  cascade = [CascadeType.MERGE])
    @JsonBackReference
    var subcategories: Set<Subcategory>? = mutableSetOf(),

    @ManyToMany(mappedBy = "sports", cascade = [CascadeType.MERGE])
    var moves: Set<Move>? = mutableSetOf(),

    @ManyToMany(mappedBy = "sports", cascade = [CascadeType.MERGE])
    var trainingSessions: Set<TrainingSession>? = mutableSetOf(),

    @ManyToMany(mappedBy = "sports", cascade = [CascadeType.MERGE])
    var trainingDevices: Set<TrainingDevice>? = mutableSetOf(),

    @ManyToMany(mappedBy = "sports", cascade = [CascadeType.MERGE])
    var trainingPlans: Set<TrainingPlan>? = mutableSetOf(),
)
{
    constructor() : this(null, "", null, null, null, null, null, null)

    fun toResponseDTO(): SportResponseDTO = SportResponseDTO(
        id = id,
        name = name,
        imagePathId = image?.id,
        subcategoryIds = subcategories!!.map { it.id },
        moveIds = moves!!.map { it.id },
        trainingDeviceIds = trainingDevices!!.map { it.id },
        trainingSessionIds = trainingSessions!!.map { it.id },
        trainingPlanIds = trainingPlans!!.map { it.id },
    )
}
