package com.jesse.c25a.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepoFlows @Inject constructor() {
    val counter: Flow<Int> = flow {
        var bombitas = 1
        while (bombitas <= 10) {
            bombitas += 1
            emit(bombitas)
            delay(1000)
        }
    }
}