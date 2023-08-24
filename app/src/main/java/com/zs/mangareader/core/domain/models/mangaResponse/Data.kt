package com.zs.mangareader.core.domain.models.mangaResponse

data class Data(

    val id: String,
    val type: String,
    val attributes: Attributes,
    val relationships: List<Relationship>

)