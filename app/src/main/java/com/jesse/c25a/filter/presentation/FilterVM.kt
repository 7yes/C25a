package com.jesse.c25a.filter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesse.c25a.burger.domain.GetDataUC
import com.jesse.c25a.burger.presentation.uiState.UIState
import com.jesse.c25a.burger.presentation.uiState.UIStateFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterVM @Inject constructor(private val getDataUC: GetDataUC) : ViewModel() {

    private val _uiState = MutableStateFlow<UIStateFilter>(UIStateFilter.Loading)
    val uiState: StateFlow<UIStateFilter> = _uiState

    init {
        getDataInicial()
    }

    fun getDataInicial() {
        _uiState.value = UIStateFilter.Loading
        viewModelScope.launch {
            try {
                val data = getDataUC()
                _uiState.value = UIStateFilter.Success(data)
            } catch (e: Exception) {
                _uiState.value = UIStateFilter.Error(e.message ?: "Unknown error")
            }
        }
    }
}