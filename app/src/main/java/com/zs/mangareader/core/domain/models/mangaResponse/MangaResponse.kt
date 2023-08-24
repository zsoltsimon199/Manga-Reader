package com.zs.mangareader.core.domain.models.mangaResponse

data class MangaResponse(

    val result: String,
    val response: String,
    val `data`: List<Data>,
    val limit: Int,
    val offset: Int,
    val total: Int

)