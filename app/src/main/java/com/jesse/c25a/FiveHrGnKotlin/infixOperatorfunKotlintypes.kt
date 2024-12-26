package com.jesse.c25.FiveHrGnKotlin

fun main(){
    val point1= Point(1,2)
    val point2 = point1 + Point(3,4)
    println(point2)//Point(x=4, y=16)

    println(1 algo 2)//6

    val miData = miData()
    val miData2:miData = "bla"

}

//se puede sobreescribir el operador es operator fun
data class Point(val x:Int, val y:Int){
    operator fun plus(other:Point):Point{
        return Point(x+other.x, y+other.y+10)
    }
}

//infix fun
infix fun Int.algo(other:Int):Int{
    return this+other*2
}

//typealias
typealias miData = String
typealias miMapa = Map<String, Int>