package com.jesse.c25a.qualifier.data

import com.jesse.c25a.qualifier.data.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {

    @GET("character")
    suspend fun getCharacters(): Response<CharacterResponse>
}