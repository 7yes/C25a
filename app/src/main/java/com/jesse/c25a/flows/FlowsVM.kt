package com.jesse.c25a.flows

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlowsVM @Inject constructor(private val repo: RepoFlows) : ViewModel() {

    private val _simpleFlow = MutableStateFlow<Int>(-5)
    val simpleFlow: StateFlow<Int> = _simpleFlow

    private val _example2 = MutableStateFlow<Int>(-5)
    val example2: StateFlow<Int> = _example2

    init {
        exampleSimple()
        example2()
    }

    fun exampleSimple() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.counter
                //.map { }
                //.onEach { }
                .collect { bombitas ->
                    Log.i("TAJF", "example: ${bombitas.toString()} ")
                    _simpleFlow.value = bombitas
                }
        }
    }

    fun example2() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.counter
                .map { (it * 2).toString() }
                .onEach { save(it) }
                .flowOn(Dispatchers.IO) //de aca para arriba esta en IOluego por default en main
                .catch { error ->
                    Log.d("TAJ", "example2:${error.message}")
                }
                .collect { bombitas ->
                    _example2.value = bombitas.toInt()
                }
        }
    }

    private fun save(it: String) {}

}