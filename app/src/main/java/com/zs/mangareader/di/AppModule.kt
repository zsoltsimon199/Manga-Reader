package com.zs.mangareader.di

import android.content.Context
import com.zs.mangareader.core.data.remote.MangaApi
import com.zs.mangareader.core.domain.helpers.ThemeDataStore
import com.zs.mangareader.core.domain.repositories.MangaRepository
import com.zs.mangareader.core.utils.Constants
import com.zs.mangareader.featureBrowse.domain.useCase.GetMangasUseCase
import com.zs.mangareader.featureBrowse.domain.useCase.MangaListUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitApi(): MangaApi {
        return Retrofit.Builder().baseUrl(Constants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MangaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMangaListUseCases(repository: MangaRepository): MangaListUseCases {
        return MangaListUseCases(
            getMangasUseCase = GetMangasUseCase(repository = repository),
        )
    }

    @Provides
    @Singleton
    fun provideThemeDataStore(@ApplicationContext context: Context): ThemeDataStore {
        return ThemeDataStore(context = context)
    }

}