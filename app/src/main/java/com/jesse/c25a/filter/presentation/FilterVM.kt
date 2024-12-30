package com.jesse.c25a.filter.presentation

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesse.c25a.burger.domain.GetDataUC
import com.jesse.c25a.burger.domain.model.SmallItem
import com.jesse.c25a.burger.presentation.uiState.UIStateFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterVM @Inject constructor(private val getDataUC: GetDataUC) : ViewModel() {

    private val _uiState = MutableStateFlow<UIStateFilter>(UIStateFilter.Loading)
    val uiState: StateFlow<UIStateFilter> = _uiState

    var datag = mutableListOf<SmallItem>()

    init {
        getDataInicial()
    }

    fun getDataInicial() {
        _uiState.value = UIStateFilter.Loading
        viewModelScope.launch {
            try {
                val data = getDataUC()
                datag = data.toMutableStateList()
                _uiState.value = UIStateFilter.Success(data)
            } catch (e: Exception) {
                _uiState.value = UIStateFilter.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun filterData(query: String) {
        val filtered = datag.filter {
            it.name?.lowercase()!!.contains(query)
        }
        _uiState.value = UIStateFilter.Success(filtered)
    }

    fun removeAt(it: Int) {
        datag.removeAt(it)
        _uiState.value = UIStateFilter.Success(datag)
    }

}