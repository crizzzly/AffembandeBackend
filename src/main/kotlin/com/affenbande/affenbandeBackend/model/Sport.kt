package com.affenbande.affenbandeBackend.model

import com.affenbande.affenbandeBackend.dto.SportResponseDTO
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
    var image: ImagePath?,

    @ManyToMany(mappedBy = "sports",  cascade = [CascadeType.ALL])
    @JsonBackReference
    var subcategories: List<Subcategory>? = mutableListOf(),

    @ManyToMany(mappedBy = "sports", cascade = [CascadeType.ALL])
    var moves: List<Move>? = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.MERGE])
    @JsonManagedReference
    @JoinTable(
        name = "t_sport_training_units",
        joinColumns = [JoinColumn(name = "fk_training_unit_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_sport_id")]
    )
    var trainingUnits: List<TrainingUnit>? = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.MERGE])
    @JsonManagedReference
    @JoinTable(
        name = "t_sport_training_devices",
        joinColumns = [JoinColumn(name = "fk_training_device_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_sport_id")]
    )
    var trainingDevices: List<TrainingDevice>? = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.ALL])
    @JsonManagedReference
    @JoinTable(
        name = "t_sport_training_plan",
        joinColumns = [JoinColumn(name = "fk_training_plan_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_sport_id")]
    )
    var trainingPlans: List<TrainingPlan>? = mutableListOf(),
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
        trainingUnitIds = trainingUnits!!.map { it.id },
        trainingPlanIds = trainingPlans!!.map { it.id },
    )
}
