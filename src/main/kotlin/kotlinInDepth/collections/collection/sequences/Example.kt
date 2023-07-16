package kotlinInDepth.collections.collection.sequences

// A sequence is a container where objects are not contained but produced
// while iterating. Those objects are not processed until it is time to use them:
// that is, sequences are executed lazily. We won't get an intermediate result
// at the end of each step.

/* Advantages:
 1) Sequences can be infinite
 2) They allow you to avoid intermediate steps
*/

fun createSequences() {
    // from elements
    val sequenceOfStrings1 = sequenceOf("one", "two", "three", "four")
    val sequenceOfIntegers = sequenceOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // from an Iterable object
    val listOfStrings = listOf("one", "two", "three", "four")
    val sequenceOfStrings2 = listOfStrings.asSequence()

    // from a function
    val sequenceOfStrings3 = generateSequence(seed = 1) { it + 1 }
        // generate sequence with the start element "1" and create later elements by incrementing by 1
        .filter { it % 2 == 0 } // left only even ones
        .take(5) // take the first 5 of them
    println(sequenceOfStrings3.toList()) // [2, 4, 6, 8, 10]
}

fun sequenceOperations() {
    /* Stateless operations */
    // these operations require no state and process
    // each element independently of their position.

    // Such functions are: map(), filter(), take() or drop()


    /* Stateful operations */
    // these operations require more amount of state, usually
    // proportional to the number of elements in the sequence.

    // Such functions are: sorted(), distinct(), chunked()
}

fun sequenceProcessing() {
    /* When we process eagerly */
    // we perform all operations on all the elements

    val withIterator = (1..10)
        .filter { print("Filter: $it, "); it % 2 == 0 } // filter out the odd numbers
        .map { print("Mapping: $it, "); it * 2 } // multiply the remaining numbers by 2
        .take(3) // take the first 3 numbers

    // Filter: 1, Filter: 2, Filter: 3, Filter: 4, Filter: 5, Filter: 6, Filter: 7, Filter: 8, Filter: 9, Filter: 10,
    // Mapping: 2, Mapping: 4, Mapping: 6, Mapping: 8, Mapping: 10,
    // Take: 4, Take: 8, Take: 12,

    // Total: 18 operations
    println(withIterator) // [4, 8, 12]


    /* When we process lazily */
    // We perform each operation after the terminal operation has been called

    val withSequence = (1..10).asSequence()
        .filter { it % 2 == 0 }
        .map { it * 2 }
        .take(3)
        .toList()

    // Filter: 1,
    // Filter: 2, Mapping: 2, Take: 4
    // Filter: 3,
    // Filter: 4, Mapping: 4, Take: 8
    // Filter: 5,
    // Filter: 6, Mapping: 6, Take: 12

    // Total: 12 operations
    println(withSequence) // [4, 8, 12]
}

fun main() {
    createSequences()
    sequenceProcessing()
}