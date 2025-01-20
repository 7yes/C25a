package com.jesse.c25a.paging3tutorial.di

import com.jesse.c25a.paging3tutorial.data.RickMortyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Paging3Qualifier

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    @Provides
    fun provideApiService(@Paging3Qualifier retrofit: Retrofit): RickMortyApiService =
        retrofit.create(RickMortyApiService::class.java)

    @Provides
    @Paging3Qualifier
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}