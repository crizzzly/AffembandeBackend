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

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "image_path_ids")
    var imagePath: ImagePath? = null,

    @ManyToMany
    @JoinTable(
        name = "t_sport_subcategory",
        joinColumns = [JoinColumn(name = "subcategory_id")],
        inverseJoinColumns = [JoinColumn(name = "sport_id")]
    )
    var sports: List<Sport>? = null,

    @ManyToMany
    @JoinTable(
        name = "t_move_subcategory",
        joinColumns = [JoinColumn(name = "subcategory_id")],
        inverseJoinColumns = [JoinColumn(name = "move_id")]
    )
    var moves: List<Move>? = null,
) {
    // No-argument constructor
    constructor() : this(null, "", null, null, null)
}
