package com.jesse.c25a.qualifier.data

import com.jesse.c25a.qualifier.data.model.Results
import javax.inject.Inject

class CharacterServ @Inject constructor(private val api: CharactersApi){

    suspend fun getCharacters(): List<Results> {
        val call = api.getCharacters()
        return if (call.isSuccessful) {
            call.body()?.results ?: emptyList()
        } else {
            emptyList()
        }
    }

}