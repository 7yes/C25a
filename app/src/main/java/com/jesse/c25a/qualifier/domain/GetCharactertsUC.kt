package com.jesse.c25a.qualifier.domain

import com.jesse.c25a.qualifier.data.CharacterRepo
import com.jesse.c25a.qualifier.domain.model.CharacterItem
import javax.inject.Inject

class GetCharactertsUC @Inject constructor(private val repo: CharacterRepo) {
    suspend operator fun invoke(): List<CharacterItem> {
        return repo.getCharacters()
    }
}