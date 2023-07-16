package kotlinInDepth.collections.collection.collectionProcessing.transformation

// transformation functions give you the ability
// to produce a new collection by changing each element
// of the existing one according to a given rule.

/* mapping */
fun mapping() {
    // Mapping transformation applies a given function
    // to each element of the original collection

    // Iterable<T> -> List<T>
    // Sequence<T> -> Sequence<T>

    println(setOf("red", "green", "blue").map { it.length } ) // [3, 5, 4]
    println(listOf(1, 2, 3, 4).map { it*it }) // [1, 4, 9, 16]
    println(intArrayOf(1, 3, 15, 16, 31, 32).map { it.toString(radix = 2) }) // [1, 11, 1111, 10000, 11111, 100000]

    val sequence = generateSequence(50) { if (it == 0) null else it/3 }
    println(sequence.map { it * 3 }.toList()) // [150, 48, 15, 3, 0]


    // If your mapping require indexes:
    println(listOf("first", "second", "third").mapIndexed { i, s -> "${i+1}. $s" })
    // [1. first, 2. second, 3. third]


    // map() hashMaps
    val hashMap = hashMapOf("I" to 1, "V" to 5, "X" to 10, "L" to 50)
    println(hashMap.map { "${it.key} is ${it.value}" })
    // [X is 10, I is 1, L is 50, V is 5]


    // Add the result to mutable collection instead of creating immutable one by adding "To":
    val result = ArrayList<String>()
    listOf("first", "second", "third").mapIndexedTo(result) { i, s -> "${i+1}. $s" }
    result.add("4. fourth") // OK
    println(result) // [1. first, 2. second, 3. third, 4. fourth]
}

/* flattering */
fun flattering() {
    // The flattening operations transform each element
    // of the original collection into a new collection
    // and then stick the resulting collections together.

    // Iterable<T> -> List<T>
    // Sequence<T> -> Sequence<T>

    println(setOf("abc", "def", "ghi").flatMap { it.asIterable() }) // [a, b, c, d, e, f, g, h, i]
    println(listOf(1, 2, 3, 4).flatMap { listOf(it) })

    // flatten() does the same, just does not transform Iterables:
    val bigList = listOf(
        listOf(1, 2),
        setOf(3, 4),
        listOf(5)
    ).flatten()
    println(bigList) // [1, 2, 3, 4, 5]
    // Remember: result is immutable
//  bigList.add(6) // Error

    // Get an mutable result instead of immutable one by adding "To" to these functions:
    val letters = ArrayList<Char>()
    setOf("abc", "def", "ghi").flatMapTo(letters) { it.asIterable() }
    letters.add('j')
    println("$letters") // [a, b, c, d, e, f, g, h, i, j]
}

/* association */
fun association() {
    // It allows you to build maps based on a given transformation
    // function and using the original collection elements


    // associateWith() builds values to given keys:

    println(listOf("red", "green", "blue").associateWith { it.length }) // {red=3, green=5, blue=4}
    println(generateSequence(1) { if (it > 50) null else it*3 }.associateWith { it.toString(radix = 2) })
    // {1=1, 3=11, 9=1001, 27=11011, 81=1010001}


    // associateBy() builds keys to given values:

    println(listOf("1", "11", "1001", "11011", "1010001").associateBy { it.toInt(radix = 2) })
    // {1=1, 3=11, 9=1001, 27=11011, 81=1010001}

    // associate() builds both key and value (Pair<K, V>):
    println(listOf("red", "green", "blue").associate { it.uppercase() to it.length })


    // Create a mutable map by adding "To" to their names:
    val OddDecToBin = HashMap<Int, String>()
    listOf(1, 3, 5, 7, 9, 11).associateWithTo(OddDecToBin) { it.toString(radix = 2) }
    println(OddDecToBin) // {1=1, 3=11, 5=101, 7=111, 9=1001, 11=1011}
}

fun main() {
    mapping()
    flattering()
    association()
}