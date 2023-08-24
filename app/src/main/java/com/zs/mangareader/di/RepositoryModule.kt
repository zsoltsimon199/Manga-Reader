package com.zs.mangareader.di

import com.zs.mangareader.core.data.repositories.MangaRepositoryImpl
import com.zs.mangareader.core.domain.repositories.MangaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsMangaRepository(
        mangaRepositoryImpl: MangaRepositoryImpl
    ) : MangaRepository

}