package com.jesse.c25.FiveHrGnKotlin

fun main() {
    val s = "this is \"escaped\" fin "
    //stringf(s)
    //arrayf()
    matrix()
}
fun matrix(){
val matrix = Array(3) { Array(3) { 0 } }
    matrix[0][0] = 1
    matrix[0][1] = 2
    println(matrix.contentDeepToString())//[[1, 2, 0], [0, 0, 0], [0, 0, 0]]
    val info = "one two"
    val (word1, word2) = info.split(" ")
    println(word1)//one
    println(word2)//two
}
fun arrayf(s:Array<Int> = arrayOf(1,4,3,3,5) ) {
    println(s[0])//1
    println(s.size)//5
    println(s.contains(1))//true
    println(s.toSet())//[1, 4, 3, 5]
    println(s.sorted())//[1, 3, 3, 4, 5]
    println(s.sortedDescending())//[5, 4, 3, 3, 1]
    println(s.reversed())//[5, 4, 3, 3, 1]
    println(s.average())//3.2
    println(s.sum())//15
    println(s.count())//5
    println(s.max())//5
    println(s.min())//1
    println(s.first())//1
    println(s.last())//5
    println(s.indexOf(3))//2
    println(s.lastIndexOf(3))//3
    println(s.distinct())//[1, 4, 3, 5]
   val zerosArrary = Array(5) { 0 }
    println(zerosArrary[2].plus(4))//4
    zerosArrary[3] = 4
    println(zerosArrary.contentToString())//[0, 0, 0, 4, 0]
    //println(zerosArrary[3].)//[0, 0, 4, 0, 0]
    println(zerosArrary.partition {
        it==0
    })//([0, 0, 0, 0], [4])
    val r= 97
    println(r.toChar())//'a'
    var a = arrayOf(2,3,4,4,5,4)
    println("a= ${a.contentToString()}")//([4, 4, 4], [2, 3, 5])
    println(a.partition { it==4 })//([4, 4, 4], [2, 3, 5])
    println(a.filter { it==4 })//[4, 4, 4]
    println(a.filterNot { it==4 })//[2, 3, 5]
    println(a.map { it*2 })//[4, 6, 8, 8, 10, 8]
}

fun stringf(s: String) {
    println(s)//this is "escaped" fin
    println(s.length)//15
    println(s.uppercase())//THIS IS "ESCAPED" FIN)
    println(s.reversed())// nif "depacse" si siht
    println(s.replace("is", "was"))//this was "escaped" fin
    println(s.dropLast(3))//this is "escaped" f
    println(s.drop(2))//is is "escaped" fin
    println("${s.substring(3, 7)}+X")//s is+X
    println(s.substring(7))// "escaped" fin
    println(s[2])//i
    println(s.contains("is"))//true
    println(s.compareTo(s))// da valor numerico 0 si es igual

    val sbb = StringBuffer("d")
    val sb= StringBuilder("Hello")
    sb.append(" World")
    println(sb.toString())//Hello World
    println(sb.append(4))//Hello World4
    println(sb.append("A"))//Hello World4A
    println(sb.insert(0, "Hi"))//Hello World4A
    println(sb.append(true))//HiHello World4Atrue
    println(sb.delete(0, 10))//rld4Atrue
}
