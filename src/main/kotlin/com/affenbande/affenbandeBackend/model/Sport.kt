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

//    @ManyToMany
//    @JoinTable(
//        name = "t_sport_image",
//        joinColumns = [JoinColumn(name = "fk_sport_id")],
//        inverseJoinColumns = [JoinColumn(name = "fk_image_id")]
//    )
    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "image_path_id")
    var image: ImagePath? = null,

    @ManyToMany(cascade = [CascadeType.PERSIST])
    @JoinTable(
        name = "t_sport_subcategory",
        joinColumns = [JoinColumn(name = "fk_sport_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_subcategory_id")]
    )
    var subcategories: List<Subcategory>? = null,

    @ManyToMany(cascade = [CascadeType.PERSIST])
    @JoinTable(
        name = "t_sport_move",
        joinColumns = [JoinColumn(name = "fk_sport_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_move_id")]
    )
    var moves: List<Move>? = null,
    ) {
    // No-argument constructor
    constructor() : this(null, "", null, null, null)
//    constructor() : this(null, "", null)
}
