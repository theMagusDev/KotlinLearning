package kotlinInDepth.collections.collection.collectionProcessing.aggregation

import kotlin.math.abs

fun common() {
    // count()
    println(listOf(1, 2, 3, 4, 5).count()) // 5
    println(listOf(1, 2, 3, 4, 5).count { it % 2 == 0} ) // 2

    // sumOf
    println(listOf("1", "2", "3", "4", "5").sumOf {it.toInt()}) // 15
    println(arrayListOf("Ann", "John", "Peter").sumOf { it.length }) // 12

    // maxOf
    println(intArrayOf(323, 511, 64, -653, 489, -2).maxOf { abs(it) }) // 653
}

fun combineIntoString() {
    println(listOf(1, 2, 3).joinToString()) // 1, 2, 3
    println(listOf(1, 2, 3).joinToString { it.toString(radix = 2) }) // 1, 10, 11
    println(listOf(1, 2, 3).joinToString(separator = "; ", prefix = "Out: ", transform = {"|$it|"} )) // Out: |1|; |2|; |3|
}

fun customAggregates() {
    // reduce() and fold(), which requires lambda with the accumulation var and how to accumulate the result.
    // fold() requires the initial acc value

    // reduce(). Its accumulator's initial value is the first element
    println(intArrayOf(1, 2, 3, 4, 5).reduce { acc, n -> acc * n } ) // calculate the array's product. Output: 120
    println(listOf("a", "b", "c", "d").reduce {acc, s -> acc + s}) // abcd
    println(listOf("a", "b", "c", "d").reduce {acc, s -> acc + s.uppercase()}) // aBCD

    // fold(). Its accumulator's initial value is given as argument
    println(listOf("a", "b", "c", "d").fold("") { acc, s -> acc + s.uppercase() } ) // ABCD
    // foldIndexed(). There is a reduceIndexed() function also.
    println(listOf("a", "b", "c", "d", "e", "f").foldIndexed("") {i, acc, s ->
        if (i % 2 == 0) {
            return@foldIndexed acc + s.uppercase()
        } else {
            return@foldIndexed acc + s.lowercase()
        }
    }) // AbCdEf
}

fun main() {
    common()
    combineIntoString()
    customAggregates()
}