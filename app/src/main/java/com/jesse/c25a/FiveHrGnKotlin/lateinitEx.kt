package com.jesse.c25.FiveHrGnKotlin

import android.util.Log

fun main() {

    //lateinit
    lateinit var lateName: M5_LateinitExample
    lateName = M5_LateinitExample("Jesse")
    lateName.initName()
    println(lateName.name)
}

class M5_LateinitExample(val newname: String) {
    lateinit var name: String
    fun initName() {
        name = newname
    }

    fun printName() {
        if (::name.isInitialized) {
            println(name)
        } else {
            Log.d("TAG", "lateinit wasn't initalized: ")
        }
    }
}