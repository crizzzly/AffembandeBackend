package com.affenbande.affenbandeBackend.dto

class SportResponseDTO(
    val id: Int?,
    val name: String,
    val imagePathId: Int?,
    val subcategoryIds: List<Int?>?,
    val moveIds: List<Int?>?
                      )