package com.affenbande.affenbandeBackend.model

import com.affenbande.affenbandeBackend.dto.SportResponseDTO
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

    @ManyToMany(mappedBy = "sports",  cascade = [CascadeType.ALL])
    @JsonBackReference
    var subcategories: List<Subcategory>? = mutableListOf(),

    @ManyToMany(mappedBy = "sports", cascade = [CascadeType.ALL])
    var moves: List<Move>? = mutableListOf(),
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
