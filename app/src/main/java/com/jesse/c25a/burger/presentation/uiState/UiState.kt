package com.jesse.c25a.burger.presentation.uiState

import com.jesse.c25a.burger.domain.model.SmallItem

sealed class UIState {
    data object Loading : UIState()
    data class Error(val error: String) : UIState()
    data class Success(val mySuccessList: List<SmallItem>) : UIState()
}
