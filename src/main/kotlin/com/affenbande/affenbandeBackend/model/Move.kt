package com.affenbande.affenbandeBackend.model

import com.affenbande.affenbandeBackend.dto.MoveResponseDTO
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name="t_moves")
class Move(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(unique = true)
    var name: String,

    // TODO: description should be a list/set - create new table "descriptions"
    @Column(length = 1000)
    var description: String? = null,


    // TODO: controller - input
    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "image_path_id")
    var image: ImagePath? = null,

    var level: Int? = null,
    var isCoreMove: Boolean? = null,
    var intensity: Int? = null,
    var repetitions: Int? = null,
    var timePreparation: Int? = null,
    var timeExercise: Int? = null,
    var formula: String? = null,
    var link: String? = null,

    @ManyToMany(cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "t_move_pre_moves",
        joinColumns = [JoinColumn(name = "fk_pre_move_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_move_id")]
    )
    var preMoves: List<Move>? = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "t_move_opt_pre_moves",
        joinColumns = [JoinColumn(name = "fk_opt_pre_move_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_move_id")]
    )
    var optPreMoves: List<Move>? = mutableListOf(),


    @ManyToMany(cascade = [CascadeType.ALL])
    @JsonManagedReference
    @JoinTable(
        name = "t_move_subcategory",
        joinColumns = [JoinColumn(name = "fk_subcategory_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_move_id")]
    )
    var subcategories: List<Subcategory>? = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.ALL])
    @JsonManagedReference
    @JoinTable(
        name = "t_move_sport",
        joinColumns = [JoinColumn(name = "fk_sport_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_move_id")]
    )
    var sports: List<Sport>? = mutableListOf(),

//    @ManyToMany(mappedBy = "moves")
//    @JsonBackReference
//    var trainingSets: List<TrainingSet>? = mutableListOf(),
) {
    // No-argument constructor
    constructor() : this(null, "", null, null, null, null, null, null, null, null,
        null, null, null, null, null, null)

    fun toResponseDTO(): MoveResponseDTO = MoveResponseDTO(
        id = id,
        name = name,
        description = description,
        subcategoryIds = subcategories?.map { it.id }!!,
        link = link,
        level = level,
        isCoreMove = isCoreMove,
        intensity = intensity,
        repetitions = repetitions,
        timePreparation = timePreparation,
        timeExercise = timeExercise,
        formula = formula,
        preMoveIds = preMoves?.map { it.id },
        optPreMoveIds = optPreMoves?.map { it.id },
        sportIds = sports?.map { it.id },
        imagePathId = image?.id
                                                          )
}




