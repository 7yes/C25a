package com.jesse.c25a.burger.presentation.uiState

import com.jesse.c25a.burger.domain.model.SmallItem

sealed class UIStateFilter {
    data object Loading : UIStateFilter()
    data class Error(val error: String) : UIStateFilter()
    data class Success(val mySuccessList: List<SmallItem>) : UIStateFilter()
}
