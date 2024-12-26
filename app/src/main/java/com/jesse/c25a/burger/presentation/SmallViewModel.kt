package com.jesse.c25a.burger.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesse.c25a.burger.domain.GetDataUC
import com.jesse.c25a.burger.presentation.uiState.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SmallViewModel @Inject constructor(private val getDataUC: GetDataUC) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState> = _uiState

    init {
        getDataIniciall()
    }

    private fun getDataIniciall() {
        _uiState.value = UIState.Loading
        viewModelScope.launch {
            try {
                val data = getDataUC()
                _uiState.value = UIState.Success(data)
                Log.d("TAJ", "getDataIniciall: $data ")
            }catch (e: Exception) {
                _uiState.value = UIState.Error(e.message ?: "Unknown error")
                Log.d("TAJ", "error ${e.message}")
            }

        }
    }
}

