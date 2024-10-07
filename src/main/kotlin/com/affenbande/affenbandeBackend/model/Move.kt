package com.affenbande.affenbandeBackend.model

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
    var setFormula: String? = null,

    @ManyToMany(cascade = [CascadeType.PERSIST])
    @JoinTable(
        name = "t_move_pre_moves",
        joinColumns = [JoinColumn(name = "move_id")],
        inverseJoinColumns = [JoinColumn(name = "pre_move_id")]
    )
    var preMoves: List<Move>? = null,

    @ManyToMany(cascade = [CascadeType.PERSIST])
    @JoinTable(
        name = "t_move_opt_pre_moves",
        joinColumns = [JoinColumn(name = "fk_move_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_opt_pre_move_id")]
    )
    var optPreMoves: List<Move>? = null,


    @ManyToMany(cascade = [CascadeType.PERSIST])
    @JoinTable(
        name = "t_sport_subcategory",
        joinColumns = [JoinColumn(name = "fk_sport_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_subcategory_id")]
    )
    var subcategories: List<Subcategory>? = null,

    @ManyToMany(cascade = [CascadeType.PERSIST])
    @JoinTable(
        name = "t_subcategory_move",
        joinColumns = [JoinColumn(name = "fk_subcategory_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_move_id")]
    )
    var sports: List<Sport>? = null,
) {


    // No-argument constructor
    constructor() : this(null, "", null, null, null, null, null, null, null, null, null)
}

//level = Column(Integer, nullable=False)
//is_core_move = Column(Boolean, nullable=False)
//descriptions = Column(Text, nullable=False)
//intensity = Column(Integer, nullable=False)
//repetitions = Column(Integer, default=0)
//time_preparation = Column(Integer, default=0)
//time_exercise = Column(Integer, nullable=False)
//set_formula = Column(String, nullable=True)