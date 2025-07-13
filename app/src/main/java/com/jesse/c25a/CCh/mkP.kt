package com.jesse.c25a.CCh

fun main() {
    val mdecBin = "4033"
    val palim = listOf("asdsa", "asdsd", "wqqw", "qrr2")
    // mdecBin(mdecBin)
    //reverseString(mdecBin)
    //getMissedNumber(mdecBin)
   // isPalindrom(palim)
}

private fun isPalindrom(palim: List<String>) {
    var isPalin = true
    palim.forEach {
        isPalin = true
        for(i in 0..(it.length/2)){
            if(it[i]==it[it.length-i-1]){}
            else isPalin=false
        }
        println(isPalin)
    }
}

private fun getMissedNumber(mdecBin: String) {
    var input = mdecBin.map { cha ->
        cha.toString().toInt()
    }
    var answer = input.toSortedSet().toMutableList()
    println(input)
    println(input.toSet())
    println(input.toSortedSet())
    println("aaa")
    var act = answer[0]
    for (i in 1..answer.size - 1) {
        if (answer[i] == act + 1) {
            act = answer[i]
        }
        println(act + 1)
    }
}

private fun reverseString(mdecBin: String) {
    var s = ""
    for (i in (mdecBin.length - 1) downTo 0)
        s += mdecBin[i]
    println(s)
}

private fun mdecBin(input: String) {
    var inputInt = input.map { cha ->
        cha.toString().toInt()
    }.toMutableList()
    println(inputInt)
    var s = ""
    var answer = mutableListOf<String>()
    var flag = true
    while (flag) {
        flag = false
        s = ""
        for (i in 0..input.length - 1) {
            if (inputInt[i] > 0) {
                inputInt[i] -= 1
                s += "1"
                flag = true
            } else {
                s += "0"
            }
        }
        answer.add(s)
    }
    answer.removeAt(answer.size - 1)
    println(answer)
}
