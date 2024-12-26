package com.jesse.c25.FiveHrGnKotlin

import kotlinx.coroutines.sync.Mutex
import kotlin.reflect.KClass

class MyClass(val name:String){
    fun printName(){
        println("My name is $name")
    } }
fun main(){
    //reflection
    val kcls: KClass<MyClass> = MyClass::class
    val cls:Class<MyClass> = MyClass::class.java
    val func = MyClass::printName

    //Mutex
    val mutex = Mutex() //in coroutine scope
   // mutex.withLock { //Mi codigo }
    mutex.tryLock()
    mutex.unlock()
    mutex.isLocked
    //mutex.lock()
}