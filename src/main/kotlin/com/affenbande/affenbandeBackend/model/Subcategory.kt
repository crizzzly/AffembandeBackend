package com.affenbande.affenbandeBackend.model

import com.affenbande.affenbandeBackend.dto.SubcategoryResponseDTO
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
    @JoinColumn(name = "image_path_ids")
    var image: ImagePath?,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JsonManagedReference
    @JoinTable(
        name = "t_sport_subcategory",
        joinColumns = [JoinColumn(name = "fk_sport_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_subcategory_id")]
    )
    var sports: List<Sport>? = mutableListOf(),

    @ManyToMany(mappedBy = "subcategories", cascade = [CascadeType.ALL])
    @JsonBackReference
    var moves: List<Move>? = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.ALL])
    @JsonManagedReference
    @JoinTable(
        name = "t_subcategory_training_units",
        joinColumns = [JoinColumn(name = "fk_training_unit_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_subcategory_id")]
    )
    var trainingUnits: List<TrainingUnit>? = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.ALL])
    @JsonManagedReference
    @JoinTable(
        name = "t_subcategory_training_devices",
        joinColumns = [JoinColumn(name = "fk_training_device_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_subcategory_id")]
    )
    var trainingDevices: List<TrainingDevice>? = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.ALL])
    @JsonManagedReference
    @JoinTable(
        name = "t_subcategory_training_plans",
        joinColumns = [JoinColumn(name = "fk_training_device_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_subcategory_id")]
    )
    var trainingPlans: List<TrainingDevice>? = mutableListOf(),
) {
    // No-argument constructor
    constructor() : this(null, "", null, null, null, null, null, null)

    fun toResponseDTO(): SubcategoryResponseDTO = SubcategoryResponseDTO(
        id = id,
        name = name,
        imagePathId = image?.id,
        sportIds = sports?.map { it.id },
        moveIds = moves?.map { it.id },
        trainingUnitIds = trainingUnits!!.map { it.id },
        trainingDeviceIds = trainingDevices!!.map { it.id },
        trainingPlanIds = trainingPlans!!.map { it.id }
    )
}
