package com.affenbande.affenbandeBackend.model

import com.affenbande.affenbandeBackend.dto.SportResponseDTO
import com.fasterxml.jackson.annotation.JsonBackReference
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
    @JoinColumn(name = "fk_image_path_id")
    var image: ImagePath?,

    @ManyToMany(mappedBy = "sports",  cascade = [CascadeType.ALL])
    @JsonBackReference
    var subcategories: List<Subcategory>? = mutableListOf(),

    @ManyToMany(mappedBy = "sports", cascade = [CascadeType.ALL])
    var moves: List<Move>? = mutableListOf(),

    @ManyToMany(mappedBy ="sports", cascade = [CascadeType.ALL])
    @JsonBackReference
    var trainingSessions: List<TrainingSession>? = mutableListOf(),


    @ManyToMany(mappedBy ="sports", cascade = [CascadeType.ALL])
    @JsonBackReference
    var trainingPlans: List<TrainingPlan>? = mutableListOf(),


    @ManyToMany(mappedBy ="sports", cascade = [CascadeType.ALL])
    @JsonBackReference
    var trainingDevices: List<TrainingDevice>? = mutableListOf(),


    @ManyToMany(mappedBy ="sports", cascade = [CascadeType.ALL])
    @JsonBackReference
    var constructionIdeas: List<ConstructionIdea>? = mutableListOf()


)
{
    constructor() : this(null, "", null, null, null, null, null, null, null)

    fun toResponseDTO(): SportResponseDTO = SportResponseDTO(
        id = id,
        name = name,
        imagePathId = image?.id,
        subcategoryIds = subcategories!!.map { it.id },
        moveIds = moves!!.map { it.id },
        trainingSessionIds = moves!!.map { it.id },
        trainingPlanIds = moves!!.map { it.id },
        trainingDeviceIds = moves!!.map { it.id },
        constructionIdeaIds = moves!!.map { it.id },
        )
}
