package com.jesse.c25a.burger.di

import com.jesse.c25a.burger.data.SmallApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://bobsburgers-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }

    @Provides
    fun providesSmallApi(retrofit: Retrofit): SmallApi {
        return retrofit.create(SmallApi::class.java)
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }
}