package com.jesse.c25.CCh

fun main() {
    val numbers = listOf(5, 20, 13, 54, 5)
    val root = Nodo(numbers[0])
    for (i in 1 until numbers.size) {
        root.add(numbers[i], root)
    }
    printBinaryTree(root)
}

data class Nodo(val value: Int, var higher: Nodo? = null, var lower: Nodo? = null) {

    fun add(value: Int, root: Nodo) {
        var node = root
        if (value > node.value) {
            if (node.higher == null) {
                node.higher = Nodo(value)
            } else {
//                node.higher?.add(value, root)
            }
        }
        else {
            if (node.lower == null) {
                node.lower = Nodo(value)
            } else {
                node.lower!!.add(value, root)
            }
        }
    }
    fun print() {
        println(value)
    }
}
fun printBinaryTree(root: Nodo?) {
 println(root?.value)
    println("${root?.lower?.value} ${root?.higher?.value}")
    var nodo = root?.lower
    println("${nodo?.lower?.value} ${nodo?.higher?.value}")
    nodo = root?.higher
    println("           ${nodo?.lower?.value} ${nodo?.higher?.value}")
}