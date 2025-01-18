package com.jesse.c25a.pag3.domain

import com.jesse.c25a.pag3.data.CharacterRepo
import com.jesse.c25a.pag3.domain.model.CharacterItem
import javax.inject.Inject

class GetCharactertsUC @Inject constructor(private val repo: CharacterRepo) {
    suspend operator fun invoke(): List<CharacterItem> {
        return repo.getCharacters()
    }
}