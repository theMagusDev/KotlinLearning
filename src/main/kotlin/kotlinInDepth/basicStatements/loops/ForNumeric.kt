package kotlinInDepth.basicStatements.loops

fun main() {
    val array = IntArray(10) {it * it}
    // [0, 1, 4, 9, 16, 25, 36, 49, 64, 81]
    val array1 = array.copyOf()
    for (i in 0..array.lastIndex) {
        if (i % 2 == 0) {
            array1[i] /= 2
        }
    }
    println(array1.contentToString())
    // [0, 1, 2, 9, 8, 25, 18, 49, 32, 81]

    // Simplify this loop
    val array2 = array.copyOf()
    for (i in 0..array.lastIndex step 2) {
        array2[i] /= 2
    }
    println(array2.contentToString())
    // [0, 1, 2, 9, 8, 25, 18, 49, 32, 81]

    // We can simplify this loop even further
    val array3 = array.copyOf()
    for (i in array3.indices step 2) {
        array2[i] /= 2
    }
    println(array3.contentToString())
    // [0, 1, 2, 9, 8, 25, 18, 49, 32, 81]

    /*
    * Many standard Kotlin types already have built-in iterators.
    * That’s why for loops work just as well for progressions,
    * arrays, and strings.
    * all a for loop needs is the presence of the iterator() function
     */
}