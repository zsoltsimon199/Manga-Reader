package com.zs.mangareader.core.data.remote

import com.zs.mangareader.core.domain.models.mangaResponse.MangaResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface MangaApi {

    @Headers("Content-Type: application/json")
    @GET("manga?includes[]=author&includes[]=artist&includes[]=cover_art")
    suspend fun getMangas(): MangaResponse

}