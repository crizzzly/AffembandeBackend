package com.affenbande.affenbandeBackend.model

import jakarta.persistence.*

@Entity
@Table(name = "t_sport")
data class Sport(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    var name: String,
    var imageSrc: String? = null,
//    @ManyToMany
//    @JoinTable(
//        name = "t_sport_subcategory",
//        joinColumns = [JoinColumn(name = "fk_sport_id")],
//        inverseJoinColumns = [JoinColumn(name = "fk_subcategory_id")]
//    )
    var subcategoryIds: List<Int>? = null,
//    @ManyToMany
//    @JoinTable(
//        name = "t_sport_move",
//        joinColumns = [JoinColumn(name = "fk_sport_id")],
//        inverseJoinColumns = [JoinColumn(name = "fk_move_id")]
//    )
    var moveIds: List<Int>? = null,
    ) {
    // No-argument constructor
    constructor() : this(null, "", null, null, null)
}
