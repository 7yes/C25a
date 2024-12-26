package com.jesse.c25.FiveHrGnKotlin

fun main() {
    val a = days.MONDAY
    a.sayHello()
    days.TUESDAY.sayHello()

    val direction = Directions.NORTH
    println(direction.description())
}

// example 1
enum class days() : Wave {
    MONDAY {
        override fun sayHello() {}
    },
    TUESDAY {
        override fun sayHello() {}
    }
}

interface Wave {
    fun sayHello()
}

// example 2
enum class Directions(private val degrees: Int) {
    NORTH(0), SOUTH(180), EAST(90), WEST(270);

    fun description(): String {
        return "$name, ordinal: $ordinal, degrees: $degrees"
    }
}

