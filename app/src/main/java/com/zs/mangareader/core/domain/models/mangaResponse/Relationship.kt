package com.zs.mangareader.core.domain.models.mangaResponse

data class Relationship(

    val id: String,
    val type: String,
    val attributes: Attributes,
    val related: String

)