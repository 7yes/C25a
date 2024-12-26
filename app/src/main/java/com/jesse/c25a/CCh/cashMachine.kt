package com.jesse.c25.CCh

fun main() {
    val readLine = readLine()
    calculateChange(readLine!!)
}

fun calculateChange(input: String) {
    val parts = input.split(":")
    val price = (parts[0].toFloat()*100).toInt()
    val paid = (parts[1].toFloat()*100).toInt()
    val change = paid - price
    println(change)
    when {
        change == 0 -> println("ZERO")
        change > 0 -> println(getChange(change))
        else -> println("ERROR")
    }
}

fun getChange(change: Int): String {
var answer = CustomerChange(change,"")
    MoneyOptions.values().forEach {
       val times =answer.change/it.amount
        answer.change -= times * it.amount
        for(i in 1..times){
            answer.message += "${it.name},"
        }
    }
    return answer.message.dropLast(1)
}

data class CustomerChange(var change: Int, var message: String)

enum class MoneyOptions(val amount: Int) {
    ONE_HUNDRED(10000),
    FIFTY(5000),
    TWENTY(2000),
    TEN(1000),
    FIVE(500),
    TWO(200),
    ONE(100),
    HALF_DOLLAR(50),
    QUARTER(25),
    DIME(10),
    NICKEL(5),
    PENNY(1)
}

