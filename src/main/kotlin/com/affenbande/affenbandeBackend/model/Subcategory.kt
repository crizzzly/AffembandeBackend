package com.affenbande.affenbandeBackend.model

import com.affenbande.affenbandeBackend.dto.response.SubcategoryResponseDTO
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

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
    var image: ImagePath?,

    @ManyToMany(cascade = [CascadeType.MERGE])
    @JsonManagedReference
    @JoinTable(
        name = "t_sport_subcategory",
        joinColumns = [JoinColumn(name = "fk_subcategory_id")],
        inverseJoinColumns = [JoinColumn(name = "fk_sport_id")]
    )
    var sports: Set<Sport>? = mutableSetOf(),

    @ManyToMany(mappedBy = "subcategories")
    @JsonBackReference
    var moves: Set<Move>? = mutableSetOf(),
) {
    // No-argument constructor
    constructor() : this(null, "", null, null, null)

    fun toResponseDTO(): SubcategoryResponseDTO = SubcategoryResponseDTO(
        id = id,
        name = name,
        imagePathId = image?.id,
        sportIds = sports?.map { it.id },
        moveIds = moves?.map { it.id }
                                                                        )
}
