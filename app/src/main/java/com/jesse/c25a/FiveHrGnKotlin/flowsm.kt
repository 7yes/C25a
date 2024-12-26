package com.jesse.c25.FiveHrGnKotlin

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

fun main(){

}

fun simpleFlow1()=flow{
        for(i in 1..10){
            emit(i)
        } }
fun simpleFlow2()=flowOf(1,2,3,4,5)
fun simpleFlow3()=listOf(1,2,3,4,5).asFlow()