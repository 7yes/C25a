package com.jesse.c25a.pag3.data

import com.jesse.c25a.pag3.data.model.toDomain
import com.jesse.c25a.pag3.domain.model.CharacterItem
import javax.inject.Inject

class CharacterRepo @Inject constructor(private val apiServ: CharacterServ) {
    suspend fun getCharacters(): List<CharacterItem> {
        return apiServ.getCharacters().map {
            it.toDomain()
        }
    }
}