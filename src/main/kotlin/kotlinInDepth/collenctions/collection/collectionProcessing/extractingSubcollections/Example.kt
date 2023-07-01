package kotlinInDepth.collenctions.collection.collectionProcessing.extractingSubcollections

// Extracting functions extract collection parts based on some criteria.

fun basicFunctions() {
    // Extract subList (result: List<T>):

    // Iterable<T> -> List<T>
    val list = List(6) { it*it } // [0, 1, 4, 9, 16, 25]
    println(list.subList(fromIndex = 1, toIndex = 4)) // [1, 4, 9]
    println(list.slice(indices = 1..3)) // [1, 4, 9]
    println(Array(6) { it*it*it }.slice(1..3)) // Array<Int> -> List<Int> [1, 8, 27]

    // Extract subArray
    // Array<T> -> Array<T>
    println(Array(6) { it*it*it }.sliceArray(1..3).contentToString()) // Array<Int> -> Array<Int> [1, 8, 27]

    // Extract custom elements
    val biggerList = List(9) { it*it } // [0, 1, 4, 9, 16, 25, 36, 49, 64]
    println(biggerList.slice(listOf(1, 3, 4))) // [1, 9, 16]
    println(biggerList.slice(biggerList.lastIndex downTo 0 step 3)) // [64, 25, 4]


    // biggerList = [0, 1, 4, 9, 16, 25, 36, 49, 64]
    // take() is used to extract a given number of elements from the first one

    println(biggerList.take(3)) // [0, 1, 4]
    println(biggerList.takeWhile { it < 30 }) // [0, 1, 4, 9, 16, 25]

    // takeLast() is used to extract a given number of elements from the last one
    println(biggerList.takeLast(3)) // [36, 49, 64]
    println(biggerList.takeLastWhile { it > 30 }) // [36, 49, 64]


    // The drop()/dropLast() functions return the remaining elements
    // when a given number of the first/last ones is removed.
    println(biggerList.drop(1)) // [1, 4, 9, 16, 25, 36, 49, 64]
    println(biggerList.dropLast(1)) // [0, 1, 4, 9, 16, 25, 36, 49]
}

fun otherFunctions() {
    // The chunked() functions allow you to split an iterable
    // or sequence into lists (called chunks) whose size
    // does not exceed a given limit.

    // Iterable<T> -> List<T>
    // Sequence<T> -> Sequence<T>

    val list = List(7) { it*it }
    println(list.chunked(3)) // [[0, 1, 4], [9, 16, 25], [36]]
    println(list.chunked(size = 3, transform = { "|$it|" })) // [|[0, 1, 4]|, |[9, 16, 25]|, |[36]|]
    println(list.chunked(size = 3, transform = { it.sum() })) // // [5, 50, 36]

    val sequence = generateSequence(1) { if (it > 300) null else it*3 }
    println(sequence.chunked(2).toList()) // [[1, 3], [9, 27], [81, 243], [729]]

    // The windowed() function extracts lists like chunked(),
    // but create them like the windows
    val anotherList = List(6) {it*it} // [0, 1, 4, 9, 16, 25]
    println(anotherList.windowed(3)) // [[0, 1, 4], [1, 4, 9], [4, 9, 16], [9, 16, 25]]
}

fun main() {
    basicFunctions()
    otherFunctions()
}