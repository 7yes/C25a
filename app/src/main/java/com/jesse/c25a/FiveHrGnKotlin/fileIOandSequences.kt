package com.jesse.c25.FiveHrGnKotlin

import java.io.File

fun main() {

    // fileIO
    val file = File("filename.txt").also {
        if (!it.exists()) {
            it.createNewFile()
            it.writeText("Hello World")
        }
    }
    file.readLines().forEach {
        println(it)
    }

    val bufferedReader = file.bufferedReader() //burredWriter
    var line: String?
    bufferedReader.use {
        line = it.readLine()
        while (line != null) {
            println(line)
            line = it.readLine()
        }
    }

    //sequence
    //    generateSequence(): Creates an infinite sequence by repeatedly applying a given function to the previous element, starting with an initial value.
    val sequence1 = generateSequence(0) { it + 1 }

    //sequenceOf(): Creates a sequence from a fixed number of elements
    val sequence2 = sequenceOf(1, 2, 3, 4, 5)

    //asSequence(): Converts an existing collection, such as a List or Set, into a sequence.
    val list = listOf(1, 2, 3)
    val sequence3 = list.asSequence()

    //yield(): Creates a sequence using a builder function with yield() to emit elements.
    val sequence4 = sequence {
        yield(1)
        yield(2)
        yield(3)
    }
    //Transforming Existing Sequences: You can create new sequences by applying operations like map, filter, take, etc., to existing sequences. These operations are also lazily evaluated.
    val sequence5 = sequenceOf(1, 2, 3, 4, 5)
        .filter { it % 2 == 0 } // Filter even numbers
        .map { it * 2 } // Multiply by
}

val sequence = sequenceOf(1, 2, 3, 4, 5)
val list = sequence.toList() //