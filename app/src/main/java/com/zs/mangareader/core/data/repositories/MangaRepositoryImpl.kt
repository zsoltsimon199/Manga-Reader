package com.zs.mangareader.core.data.repositories

import com.zs.mangareader.core.data.remote.MangaApi
import com.zs.mangareader.core.domain.models.Resource
import com.zs.mangareader.core.domain.models.mangaResponse.MangaResponse
import com.zs.mangareader.core.domain.repositories.MangaRepository
import retrofit2.HttpException
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val api: MangaApi
): MangaRepository {

    override suspend fun getMangaResponse(): Resource<MangaResponse> {
        return try {
            Resource.Success(data = api.getMangas())
        } catch (e: HttpException) {
            Resource.Error(message = e.message ?: "Something went wrong")
        }
    }

}