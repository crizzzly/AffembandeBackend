package com.affenbande.affenbandeBackend.model

import jakarta.persistence.*
import kotlin.Int

@Entity
@Table(name = "t_subcategories")
class Subcategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column(unique = true)
    var name: String,

//    @ManyToMany
//    @JoinTable(
//        name = "t_subcategory_image",
//        joinColumns = [JoinColumn(name = "fk_subcategory_id")],
//        inverseJoinColumns = [JoinColumn(name = "fk_image_id")]
//    )
    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "image_path_ids")
    var image: ImagePath? = null,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "t_sport_subcategory",
        joinColumns = [JoinColumn(name = "fk_subcategory_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_sport_id")]
    )
    var sports: List<Sport>? = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "t_move_subcategory",
        joinColumns = [JoinColumn(name = "fk_subcategory_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_move_id")]
    )
    var moves: List<Move>? = mutableListOf(),
) {
    // No-argument constructor
    constructor() : this(null, "", null, null, null)
}
