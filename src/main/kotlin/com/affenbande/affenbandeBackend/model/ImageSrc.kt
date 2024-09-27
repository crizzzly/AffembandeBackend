package com.affenbande.affenbandeBackend.model

import jakarta.persistence.*

@Entity
@Table(name = "t_image_paths")
data class ImageSrc(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(unique = true)
    var name: String,
    var xs: String? = null,
    var s: String? = null,
    var m: String? = null,
    var l: String? = null,
    var xl: String? = null
)
{
    // No-argument constructor
    constructor() : this(null, "", null, null, null, null, null)
}

