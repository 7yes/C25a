package com.jesse.c25a.pag3.presentation.uiState

import com.jesse.c25a.pag3.domain.model.CharacterItem

sealed class UIState {
        data object Loading: UIState()
        data class Error(val error:String): UIState()
        data class Success(val mySuccessList:List<CharacterItem>): UIState()
}