package com.jesse.c25a.CCh

import android.os.Build
import androidx.annotation.RequiresApi
import com.jesse.c25a.Income.toIntOrZero

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun main() {
    val input = 402
    //   decBin(input)
    //  println(transformDigits(input))
      myDecBin(input)
    //  pruebas(input)
   // myDecBin2(input)
}

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
private fun myDecBin2(input: Int) {
    val aa = input.toString().map { it.toString().toInt() }.toMutableList()
    println(aa)
    var answer = mutableListOf<List<Int>>()
    var word = mutableListOf<Int>()
    var doItAgain = true
    do {
        doItAgain = false
        word = mutableListOf<Int>()
        for (i in 0..aa.size - 1) {
            if (aa[i] > 0) {
                aa[i] = aa[i] - 1
                word.add(1)
                doItAgain = true
            } else word.add(0)
        }
        println("bb $word")
        answer.add(word)
    } while (doItAgain)
    println("answerList ${answer}")
    answer = answer.dropLast(1).toMutableList()
    println("answerDrop ${answer}")
    val f = answer.map {
        it.toString()
    }
    println("f ${f}")

    var answerListStrings = mutableListOf<String>()
    answer.forEach { wordInt ->
        var word2String=""
        wordInt.forEach { letra->
            word2String+=letra
        }
        println("word2String $word2String")
        answerListStrings+=word2String
    }
    println("answerListStrings $answerListStrings")
}

private fun pruebas(input: Int) {
    val s = input
    val b = s.toString().map {
        listOf(it)
    }

    println("string $s")
    println("lista ${listOf(s)}")
    println("lista $b")
}


private fun myDecBin(input: Int) {
    var answer = ""
    val s = input.toString()
    val arrayInput = s.map {
        it.toString().toInt()
    }.toMutableList()
    var doit = false
    do {
        doit = false
        var word = ""
        for (i in 0..arrayInput.size - 1) {
            if (arrayInput[i] > 0) {
                word += "1"
                arrayInput[i] -= 1
                doit = true
            } else
                word += 0
        }
        println(word)
        if (word.toInt() > 0) answer += " $word"
    } while (doit)
    println("//")
    println(arrayInput)
    println("anwer = $answer")
    val parts = answer.trim().split(" ")
    var mle = mutableListOf<Int>()
    println("parts $parts")
    for (i in parts) {
        mle.add(i.toIntOrZero())
    }
    println("mle : $mle")
    val ans2: MutableList<List<String>> = mutableListOf()
    for (i in parts) {
        ans2.add(listOf(i))
    }
    println(ans2)
}

private fun decBin(input: Int) {
    val s = input.toString()
    val arrInput = Array(s.length) { 0 }
    var counter = 0
    for (c in s) {
        arrInput[counter++] = c.toString().toInt()
    }
    for (i in arrInput.indices) {
        println(arrInput[i])
    }
    var word = ""
    var hadChange = true
    for (x in 1..9) {
        hadChange = false
        for (i in arrInput.indices) {
            hadChange = true
            if (arrInput[i] > 0) {
                word += "1"
                arrInput[i] -= 1
            } else word += "0"
        }
        word += " "
    }
    print(word)
}

private fun transformDigits(input: Int): String {
    if (input < 0) {
        // Or handle negative numbers differently, e.g., throw IllegalArgumentException
        return "Input must be non-negative"
    }

    val digits = input.toString().map { it.toString().toInt() }.toMutableList()
    print(" digits: $digits")
    val resultBuilder = StringBuilder()

    // The number of iterations should ideally be dynamic or based on the max digit,
    // but sticking to 9 to match the original logic for now.
    // A better approach would be to loop while any digit is > 0 or up to the max digit value.
    val maxIterations = digits.maxOrNull() ?: 0 // Determine max digit for smarter looping
    // Or stick to 9 if that's a hard requirement
    println("maxIte $maxIterations")

    for (iteration in 1..maxIterations) { // Loop up to the max digit value
        // or use 1..9 if 9 iterations is a fixed requirement
        var somethingChangedInThisIteration = false
        for (i in digits.indices) {
            if (digits[i] > 0) {
                resultBuilder.append('1')
                digits[i]--
                somethingChangedInThisIteration = true
            } else {
                resultBuilder.append('0')
            }
        }
        if (iteration < maxIterations || (maxIterations == 0 && input == 0)) { // Add space unless it's the very last iteration
            // Or if using fixed 9 iterations: iteration < 9
            resultBuilder.append(' ')
        }
        // If no digits were decremented in this iteration, and all digits are zero,
        // we can potentially break early if using dynamic iterations.
        if (!somethingChangedInThisIteration && digits.all { it == 0 } && maxIterations > 0) { // only break if we are using dynamic iterations
            if (iteration >= (digits.maxOrNull() ?: 0)) // ensure we did at least one pass for "0"
                break
        }
    }
    println("res")
    return resultBuilder.toString()
}