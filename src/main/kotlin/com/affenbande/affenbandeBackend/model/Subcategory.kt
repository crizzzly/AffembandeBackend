package com.affenbande.affenbandeBackend.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
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
    var image: ImagePath? = null,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JsonManagedReference
    @JoinTable(
        name = "t_sport_subcategory",
        joinColumns = [JoinColumn(name = "fk_subcategory_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_sport_id")]
    )
    var sports: List<Sport>? = mutableListOf(),

    @ManyToMany(mappedBy = "subcategories", cascade = [CascadeType.ALL])
    @JsonBackReference
    var moves: List<Move>? = mutableListOf(),
) {
    // No-argument constructor
    constructor() : this(null, "", null, null, null)
}
