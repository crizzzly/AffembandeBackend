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
    var imageSrc: String? = null,
    var description: String? = null,
//    @ManyToMany
//    @JoinTable(
//        name = "t_sport_subcategory",
//        joinColumns = [JoinColumn(name = "sport_id")],
//        inverseJoinColumns = [JoinColumn(name = "subcategory_id")]
//    )
//    var subcategoryIds: List<Int>? = null,
//    @ManyToMany
//    @JoinTable(
//        name = "t_subcategory_move",
//        joinColumns = [JoinColumn(name = "subcategory_id")],
//        inverseJoinColumns = [JoinColumn(name = "move_id")]
//    )
//    var sportIds: List<Int>? = null,
) {
    // No-argument constructor
    constructor() : this(null, "", null, null)
}
