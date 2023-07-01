package kotlinInDepth.collenctions.iterables

// The Iterable type itself is rather similar to its Java counterpart. It provides
//a single iterator() method which returns an object capable of traversing
//its elements.

fun program1() {
    val list = listOf<String>("red", "green", "blue")
    for (item in list) {
        print("$item ")
    } // red green blue
    println()
}

/*

 Java vs Kotlin: The Kotlin’s Iterator type is basically the same as Java’s. It
 contains two methods: hasNext() which checks whether the iterator has
 reached the end of collection, and next() which returns the next collection
 element. The only difference is the absence of the remove() method which
 is moved to MutableIterator instead.

 */

