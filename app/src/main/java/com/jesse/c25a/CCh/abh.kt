package com.jesse.c25a.CCh

fun main2() {
    val input1 = intArrayOf(1, 0, 0, 1, 0)
    val a1 = arrayOf(1, 0, 0, 1, 0)
    val a2 = arrayOf(1, 3, 3, 2)
    val a3 = arrayOf(1, 0, 2, 0, 3, 0)
    //  exe1(a1, 1, 3)
    //exe2(a2)
    //exe2f(a2)
    exe3(a3)
}

fun exe1(ints: Array<Int>, i1: Int, i2: Int) {
    for (i in ints.indices)
        print(ints[i])
    println("")
    for (i in i1..i2)
        if (ints[i] == 0) ints[i] = 1 else ints[0]
    for (i in ints.indices)
        print(ints[i])
    var c = 0  //Input: arr = [1, 0, 0, 1, 0] Output: 4
    for (i in ints.indices) // Explanation: Flip subarray from index 1 to 3
        if (ints[i] == 1) c++ // → becomes [1, 1, 1, 0, 0] → total 1s = 4
    println("\n$c") //flip
}

fun exe2(ints: Array<Int>) {
    val cop = Array(ints.size) { 0 }
    for (i in ints.indices) {
        print(ints[i])
        cop[i] = ints[i]
    }
    print("\n")
    for (i in cop.indices) {
        print(cop[i])
        cop[i] = cop[i]
    }
    print("\n")
    for (i in 1..cop.size - 1) {
        if (cop[i] > cop[i - 1]) {
        } else cop[i] = cop[i - 1] + 1
    }
    print("\n")
    for (i in cop.indices) {
        print(cop[i])
        cop[i] = cop[i]
    }
    print("\n")
    var c = 0
    for (i in ints.indices) {
        c = c + cop[i] - ints[i]
    }
    println(c)
}

fun exe2f(ints: Array<Int>) {
    var c = 0 // [1, 3, 3, 2] -> [1, 3, 4, 5]
    for (i in 1..ints.size - 1) {
        if (ints[i] > ints[i - 1]) {
        } else {
            c += ints[i - 1] - ints[i] + 1
            ints[i] = ints[i - 1] + 1
        }
    }
    for (i in ints.indices)
        print(ints[i])
    println("\n")
    print(c)
}

fun exe3(ints: Array<Int>) {
    val res = Array(ints.size) { 0 }
    var c = 0
    for (i in ints.indices) {
        if (ints[i] == 0) {
        } else {
            res[c] = ints[i]
            c++
        }
    }
    for (i in res.indices)
        print(res[i])
}

data class TreeNode(val value: Int, val left: TreeNode? = null, val right: TreeNode? = null)

fun zigzagTraversal(root: TreeNode?): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    if (root == null) return result
    val queue: ArrayDeque<TreeNode> = ArrayDeque()
    queue.add (root)
    var leftToRight = false
    while (queue.isNotEmpty()) {
        val levelSize = queue.size
        val levelList = ArrayDeque<Int>()
        repeat(levelSize) {
            val node = queue.removeFirst()
            if (leftToRight) {
            levelList.addLast(node.value)
        } else {
            levelList.addFirst(node.value)
        }
            node. left ?. let { queue.addLast(it) }
            node.right ?. let { queue.addLast(it) }
        }
        result. add (levelList.toList())
        leftToRight = !leftToRight
    }
    return result
}

fun main() {
    val tree = TreeNode(
        1,
        TreeNode(2, TreeNode(4), TreeNode(5)),
        TreeNode(3, TreeNode(6), TreeNode(7))
    )
    println (zigzagTraversal(tree))
} // Output: [[1], [3, 2], [4, 5, 6, 7]] }`