package com.zs.mangareader.core.domain.repositories

import com.zs.mangareader.core.domain.models.Resource
import com.zs.mangareader.core.domain.models.mangaResponse.MangaResponse

interface MangaRepository {

    suspend fun getMangaResponse(): Resource<MangaResponse>

}