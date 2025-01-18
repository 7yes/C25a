package com.jesse.c25a.pag3.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesse.c25a.pag3.domain.GetCharactertsUC
import com.jesse.c25a.pag3.presentation.uiState.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatacterVM @Inject constructor(private val getCharactertsUC: GetCharactertsUC) :
    ViewModel() {
    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState> = _uiState

    init {
        getCharacters()
    }

    private fun getCharacters() {
        _uiState.value = UIState.Loading
        Log.d("TAJ", "getCharacters: loading")
        viewModelScope.launch {
            try {
                val data = getCharactertsUC()
                _uiState.value = UIState.Success(data)
                Log.d("TAJ", "getCharacters: $data")
                Log.d("TAJ", "getCharacters: size = ${data.size}")
            } catch (e: Exception) {
                _uiState.value = UIState.Error(e.message ?: "Unknown error")
            }
        }
    }
}