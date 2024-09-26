package com.affenbande.affenbandeBackend.model

import jakarta.persistence.*
import kotlin.Int

@Entity
@Table(name = "t_subcategory")
data class Subcategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    var name: String,
    var imageSrc: String? = null,
//    @ManyToMany
//    @JoinTable(
//        name = "t_sport_subcategory",
//        joinColumns = [JoinColumn(name = "subcategory_id")],
//        inverseJoinColumns = [JoinColumn(name = "sport_id")]
//    )
    var sportIds: List<Int>? = null,
//    @ManyToMany
//    @JoinTable(
//        name = "t_move_subcategory",
//        joinColumns = [JoinColumn(name = "subcategory_id")],
//        inverseJoinColumns = [JoinColumn(name = "move_id")]
//    )
    var moveIds: List<Int>? = null,
) {
    // No-argument constructor
    constructor() : this(null, "", null, null, null)
}
