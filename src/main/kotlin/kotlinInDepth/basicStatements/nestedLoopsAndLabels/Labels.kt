package kotlinInDepth.basicStatements.nestedLoopsAndLabels

fun indexOf(subarray: IntArray, array: IntArray): Int {
    outerLoop@ for (i in array.indices) {
        for (j in subarray.indices) {
            if (subarray[j] != array[i + j]) {
                continue@outerLoop
            }
        }
        return i
    }
    return -1
}

fun main() {
    val numbers = intArrayOf(1, 3, 5, 7, 9, 11, 13, 15)
    val myNumbers1 = intArrayOf(1, 5, 6)
    val myNumbers2 = intArrayOf(3, 5)
    println(indexOf(myNumbers1, numbers))
    println(indexOf(myNumbers2, numbers))

    // Note: you can attach the label to ANY statement
    abcFun@ fun abc(a: Int): Unit {
        return@abcFun
    }
}

/*
Qualified returns allow us to return from an outer function.
The most important use case is returning from a
lambda expression.
 */
fun foo() {
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit
        // usual 'return' will be applied to foo() instead of forEach
        println(it)
    }
    println(" done with explicit label")
}

// Often it is more convenient to use implicit labels,
// because such a label has the same name as the function
// to which the lambda is passed.
fun foo1() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach
        // local return to the caller of the lambda - the forEach loop
        println(it)
    }
    println(" done with implicit label")
}
