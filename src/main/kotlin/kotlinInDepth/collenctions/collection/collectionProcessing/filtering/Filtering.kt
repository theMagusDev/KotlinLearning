package kotlinInDepth.collenctions.collection.collectionProcessing.filtering

// A filtering operation does not modify an original collection.
// It either produces an entirely new one

// Filtering Array<T> or Iterable<T> gives a List<T>
// Filtering Map<T> gives a Map<T>
// Filtering Sequence<T> gives a Sequence<T>

fun example() {
    println(listOf("red", "green", "blue", "purple").filter { it.length > 4 } ) // [green, purple]
    println(setOf("Yaroslav", "Gleb", "spoon", "cat", "Igor").filter { it.first().isUpperCase() }) // [Yaroslav, Gleb, Igor]
    println(arrayOf("Hey!", "Hello.", "Greetings!", "Hi!", "What's up?").filter {
        it.length >= 5
        it.last() == '!'
    }) // [Hey!, Greetings!, Hi!]

    // filtering map as usual
    println(hashMapOf("I" to 1, "V" to 5, "X" to 10, "L" to 50).filter {
        it.value <= 10
    }) // {X=10, I=1, V=5}

    // filtering map's keys or values
    println(hashMapOf("I" to 1, "V" to 5, "X" to 10, "L" to 50).filterKeys {
        it != "L"
    }) // {X=10, I=1, V=5}
    println(hashMapOf("I" to 1, "V" to 5, "X" to 10, "L" to 50).filterValues {
        it % 5 == 0
    }) // {X=10, L=50, V=5}


    // filterNot() - filter if not satisfying condition
    println((1..100).filterNot { it % 10 != 0 } ) // [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]

    // filterIndexed() - filter with condition, depending on index
    println(listOf("Peter", "John", "Tom").filterIndexed { i, s -> i % 2 == 0 } ) // [Peter, Tom]

    println(listOf(1, 4, null, 6, null, null).filterNotNull()) // [1, 4, 6]
}

// The filtering functions we’ve seen so far produce new
// immutable collections on each call. If we need to put
// the filtering results to some mutable collection, then
// there are functions with "To" added to their names.
fun example1() {
    val nums = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val immutableResult = nums.filter { it % 2 != 0 }
//  immutableResult.add(111) // Error: immutableResult is immutable IntArray

    val mutableResult = ArrayList<Int>()
    nums.filterTo(mutableResult) { it % 2 != 0 }
    mutableResult.add(111) // OK
}

// partition() function splits the original collection into a pair, where
// the first element satisfy the condition, and the second doesn't
fun example2() {
    val (even, odds) = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).partition { it % 2 == 0 }
    println(even) // [2, 4, 6, 8, 10]
    println(odds) // [1, 3, 5, 7, 9]

    val map = hashMapOf(1 to "one", 2 to "two", 3 to "three")
}

fun main() {
    example()
    example1()
    example2()
}