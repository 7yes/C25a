package com.jesse.c25.FiveHrGnKotlin

fun main(){
    val person = Person()
    println(person.name)
    person.name = "Jane"
    println(person.name)

    val alumno = alumno("John", 20)
    val(name1, age) = alumno
    println(name1)
    println(age)
}

data class alumno(val name:String, val age:Int){

}
private class Alumno(val name:String, val age:Int)

class Person(){
    var name = "John"
    get() = "the name is ${field.uppercase()}"
    set(value){
        field = value.plus("X")
    }
}