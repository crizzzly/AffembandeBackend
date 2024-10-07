package com.affenbande.affenbandeBackend.model

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
    @JoinColumn(name = "image_path_id")
    var image: ImagePath? = null,

    @ManyToMany(mappedBy = "sports",  cascade = [CascadeType.ALL])
    @JsonBackReference
    var subcategories: List<Subcategory>? = mutableListOf(),

    @ManyToMany(mappedBy = "sports", cascade = [CascadeType.ALL])
    var moves: List<Move>? = mutableListOf(),
    ) {
    // No-argument constructor
    constructor() : this(null, "", null, null, null)
//    constructor() : this(null, "", null)
}
