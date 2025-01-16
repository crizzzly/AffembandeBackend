package com.affenbande.affenbandeBackend.model

import com.affenbande.affenbandeBackend.dto.response.SubcategoryResponseDTO
import com.affenbande.affenbandeBackend.model.training.TrainingSession
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "t_subcategories")
class Subcategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column(unique = true)
    var name: String,

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "image_path_id")
    var image: ImagePath? = null,

    @ManyToMany(cascade = [CascadeType.MERGE])
    @JsonManagedReference
    @JoinTable(
        name = "t_sport_subcategory",
        joinColumns = [JoinColumn(name = "fk_sport_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_subcategory_id")]
    )
    var sports: Set<Sport>? = mutableSetOf(),

    @ManyToMany(mappedBy = "subcategories", cascade = [CascadeType.MERGE])
    @JsonBackReference
    var moves: Set<Move>? = mutableSetOf(),

    @ManyToMany(mappedBy = "subcategories", cascade = [CascadeType.MERGE])
    var trainingSessions: Set<TrainingSession>? = mutableSetOf(),

//    @ManyToMany(cascade = [CascadeType.MERGE])
//    @JsonManagedReference
//    @JoinTable(
//        name = "t_subcategory_training_devices",
//        joinColumns = [JoinColumn(name = "fk_training_device_id")],
//        inverseJoinColumns = [JoinColumn(name = "fk_subcategory_id")]
//    )
//    var trainingDevices: Set<TrainingDevice>? = mutableSetOf(),

//    @ManyToMany(cascade = [CascadeType.MERGE])
//    @JsonManagedReference
//    @JoinTable(
//        name = "t_subcategory_training_plans",
//        joinColumns = [JoinColumn(name = "fk_training_device_id")],
//        inverseJoinColumns = [JoinColumn(name = "fk_subcategory_id")]
//    )
//    var trainingPlans: Set<TrainingPlan>? = mutableSetOf(),
) {
    // No-argument constructor
    constructor() : this(null, "", null, null, null)

    fun toResponseDTO(): SubcategoryResponseDTO = SubcategoryResponseDTO(
        id = id,
        name = name,
        imagePathId = image?.id,
        sportIds = sports?.map { it.id },
        moveIds = moves?.map { it.id },
//        trainingSessionIds = trainingSessions!!.map { it.id },
//        trainingDeviceIds = trainingDevices!!.map { it.id },
//        trainingPlanIds = trainingPlans!!.map { it.id }
    )
}