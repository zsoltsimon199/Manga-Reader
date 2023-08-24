package com.zs.mangareader.featureBrowse.domain.useCase

import com.zs.mangareader.core.domain.models.Resource
import com.zs.mangareader.core.domain.models.mangaResponse.MangaResponse
import com.zs.mangareader.core.domain.repositories.MangaRepository
import javax.inject.Inject

class GetMangasUseCase @Inject constructor(
    private val repository: MangaRepository
) {

    suspend fun execute(): Resource<MangaResponse> {
        return repository.getMangaResponse()
    }

}