package com.jesse.c25a.CCh

import kotlin.math.abs

fun main() {
//    val k = 8
//    val n = 3
//    solve(k, n)
    solve2(3,"qwerty","wwwqwerty")
}

fun solve2(N: Int, C: String, w: String) {
   var posIni = C.indexOf(w[0])
   var count = 0
    var pos = 0

   w.forEach {
       pos =C.indexOf(it)
       count += abs(posIni-pos)
       posIni = pos
   }
println("final $count")
}


fun solve(k: Int, n: Int) {
    var c = 0
    var ini = k
    var prime = false
    var complete = false
    while (!complete) {
        println("reviso $ini")
        prime = isPrime(ini)
        if (!prime) ini++
        else {
            c++
            if (c != n) {
                complete = false
                ini++
            } else complete = true
        }
    }
    println(" el primo es $ini")
}

fun isPrime(number: Int): Boolean {
    if (number <= 1) {
        return false
    }
    for (i in 2..Math.sqrt(number.toDouble()).toInt()) {
        print("t $i")
        if (number % i == 0) {
            return false
        }
    }
    return true
}
