package com.affenbande.affenbandeBackend.model

import com.affenbande.affenbandeBackend.dto.response.SportResponseDTO
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
    var image: ImagePath?,

    @ManyToMany(mappedBy = "sports")
    @JsonBackReference
    var subcategories: Set<Subcategory>? = mutableSetOf(),

    @ManyToMany(mappedBy = "sports")
    var moves: Set<Move>? = mutableSetOf(),
    )
{
    constructor() : this(null, "", null, null, null)

    fun toResponseDTO(): SportResponseDTO = SportResponseDTO(
        id = id,
        name = name,
        imagePathId = image?.id,
        subcategoryIds = subcategories!!.map { it.id },
        moveIds = moves!!.map { it.id }
                                                            )
}
