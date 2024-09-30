package com.affenbande.affenbandeBackend.model

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
    @JoinColumn(name = "image_path_ids")
    var imagePath: ImagePath? = null,
//    @ManyToMany
//    @JoinTable(
//        name = "t_sport_subcategory",
//        joinColumns = [JoinColumn(name = "fk_sport_id")],
//        inverseJoinColumns = [JoinColumn(name = "fk_subcategory_id")]
//    )
//    var subcategoryIds: List<Int>? = null,
//    @ManyToMany
//    @JoinTable(
//        name = "t_sport_move",
//        joinColumns = [JoinColumn(name = "fk_sport_id")],
//        inverseJoinColumns = [JoinColumn(name = "fk_move_id")]
//    )
//    var moveIds: List<Int>? = null,
    ) {
    // No-argument constructor
    constructor() : this(null, "")
//    constructor() : this(null, "", null)
}
