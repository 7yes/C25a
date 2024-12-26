package com.jesse.c25.FiveHrGnKotlin

fun main() {

    val result = operationNumbers(2, 3) { a, b ->
        a + b
    }
    println(result)//5
    val result2 = getMathOperation("suma")(2, 3)
    println(result2)//5
    val set = setOf(1,3,4,3)//[1, 3, 4]
    val mset = mutableSetOf(1,3,4,5,3)//[1, 3, 4, 5]
    val map = mapOf("uno" to 1, "dos" to 2, "tres" to 3)
    val mmap = mutableMapOf("uno" to 1, "dos" to 2, "tres" to 3)
    val list = listOf(1, 2, 3)
    val mlist = mutableListOf(1, 2, 3)
    mset.add(9)
    mset.remove(3)
    mmap["cuatro"] = 5
    mmap.remove("tres")
    mlist.add(4)
    mlist.remove(2)
    println("set: $set mset: $mset map: $map mmap: $mmap list: $list mlist: $mlist")
    println( mlist.asReversed())
    println( mlist)
    println( mlist.partition {
        it>3
    }.second)


    val pair = Pair(1, "uno")
    val (first, second) = pair
    println(first)//1
    println(second)//uno

    val Triple = Triple(1, "uno", 1.0)
    val (first1, second1, third1) = Triple
    println(first1)//1
    println(second1)//uno
    println(third1)//1.0
}

fun getMathOperation(ope: String): (Int, Int) -> Int {
    return when (ope) {
        "suma" -> { a, b -> a + b }
        "resta" -> { a, b -> a - b }
        else -> { _, _ -> 0 }
    }
}

fun operationNumbers(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}