package kotlinInDepth.languageFundamentals

import java.util.Date

fun main() {

    /* Integer */

    val a = 34_721_189 // 34721189

    val one: Byte = 1 // OK
    // val tooBigForShort: Short = 100_000 // Error: too big for Short
    val million = 1_000_000 // OK: Int is inferred
    // val tooBigForInt: Int = 10_000_000_000 // Error: too big for Int
    val tenBillions = 10_000_000_000 // OK: Long is inferred
    // val tooBigForLong = 10_000_000_000_000_000_000 // Error: too big for Long
    val hundredLong = 100L // OK: Long is inferred
    // val hundredInt: Int = 100L // Error: assigning Long to Int
    val bin = 0b10101 // 21
    val hex = 0xF9 // 249
    /*
    * octal numbers are not supported in Kotlin as
    * it is rarely useful and often a misleading feature
     */

    // zero-prefixed numbers are forbidden
    val zero = 0 // OK
    // val zeroOne = 01 // Error

    println(Int.MAX_VALUE) // 2147483647
    println(Int.MIN_VALUE) // -2147483648


    /* Floating-point */

    val pi = 3.14
    // val one = 1. // Error
    val pi1 = 0.314E1 // 3.14E1 = 0.314 * 10**1 = 3.14
    val thousand = 1E3 // 1E3 = 1 * 10**3 = 1000

    val pi2 = 3.14f // Float type

    /*Note: Float literals are not automatically converted to Double types
    * val pi3: Double = 3.14f;
    * Error: The floating-point literal does not conform to the expected type Double
    * values of smaller-range types cannot be used in the context where the larger type is expected.
    * Just like in Java:
    * Integer n = 100;
    * Long l = n; // Error: can’t assign Integer to Long
    * */

    /* Char */

    val z = 'z'
    val alpha = 'α'

    // special characters
    /*
    \t (tab),
    \b (backspace),
    \n (newline),
    \r (carriage return),
    \’ (single quote),
    \” (double quote),
    \\ (backslash),
    \$ (dollar sign):
     */
    println("Balance: \$50")

    // Unicode
    val pi4 = '\u03C0' // π

    val b: Char = 'b'
    println(b + 1) // c
    println(b - 1) // a
    println('e' - b) // 3


    /* Numeric conversions */

    // converting a very big Long value to Float can zero some lower digits
    println(1_000_000_000_000.toFloat().toLong()) // 999999995904

    /* Unsigned */
    // Can be only positive
    val positiveA: UInt = 5u // OK
    // val positiveB: UInt = 5 // Error: The integer literal does not conform to the expected type UInt

    /* Boolean type and logical operations */

    // Lazy disjunction/conjunction
    var i = 0
    if (true || i++ > 0) {
        println(i) // 0
    }
    if (false && i++ > 0) {}
    println(i) // 0

    /* Note:
    * Kotlin doesn’t have '&' and '|' operators.
    * '&' in Java -> 'and' in Kotlin
    * '|' in Java -> 'or' in Kotlin
     */

    /* Comparison and equality */

    val a1 = 1 // Int
    val b1 = 2L // Long
    // println(a1 == b1) // Error: comparing Int and Long
    println(a1.toLong() == b1) // Ok: both types are Long
    // But
    1 <= 2L || 3 > 4.5 // OK


    /* Strings */
    /* String templates */

    val text = "Hello, world!\nThis is \"multiline\" string"
    println(text)
    /* Output:
    Hello, world!
    This is "multiline" string
     */
    println("\u03C0 \u2248 3.14") // π ≈ 3.14


    fun greet(name: String?) {
        println("Hello, $name! \nToday is ${Date()}")
    }
    val name = "Yuriy"
    greet(name)

    // Raw string
    fun advancedGreet(name: String?) {
        val message = """
            Hello, $name!
            Today is ${Date()}
        """.trimIndent()
        println(message)
    }
    // The trimIndent() is a standard Kotlin function which
    // removes the common minimal indent
    advancedGreet(name)
    fun advancedGreet2(name: String?) {
        val message = """
                        |Hello, $name!
                |Today is ${Date()}
        """.trimMargin("|")
        println(message)
    }
    advancedGreet2(name) // same output

    val messageWithTripleQuote = """
        This is triple quote: ${"\"\"\""}
    """.trimIndent()
    println(messageWithTripleQuote) // This is triple quote: """

    /* Basic string operations */
    "Hello!".length // 6
    "Hello!".lastIndex // 5 since indices start from zero

    val s1 = "Hello!"
    val s2 = "Hel" + "lo!"
    println(s1 == s2) // true
    // is equivalent to Java’s s1.equals(s2)
    // Note: In Kotlin, == is basically a more convenient synonym for equals()
    // But what about referential equality in Kotlin?
    // For that, you can use === and !== operators.
    s1.length // 6
    s1.lastIndex // 5


    /* Arrays */


    val array1 = emptyArray<String>() // Array<String> (zero elements)
    val array2: Array<String> = arrayOf("hello", "world") // Array<string> (2 elements)
    val array3 = arrayOf(1, 4, 9) // Array<Integer> (3 elements)

    // create an array by describing how to compute an element with a given index
    val size = 4
    val squares = Array(size, { elementIndex ->  (elementIndex + 1) * (elementIndex + 1) })
    println(squares[3])

    /*
    * Using Array<Int> is working, but it is an impractical solution since it will
    force the boxing of numbers. For this reason, Kotlin provides a more
    efficient storage with specialized array types such as ByteArray,
    ShortArray, IntArray, LongArray, FloatArray, DoubleArray,
    CharArray, and BooleanArray. On JVM, these types are represented by
    Java primitive arrays such as int[] or boolean[]. Each of them is
    accompanied by functions similar to arrayOf() and Array()
     */
    val operations = charArrayOf('+', '-', '*', '/', '%')
    val primitiveSquares = IntArray(10, { elementIndex -> (elementIndex + 1) * (elementIndex + 1) })
    // Note: in Kotlin, you have to explicitly initialize array elements on its creation.

    /* Using arrays */

    val fourSquares = arrayOf(1, 4, 9, 16)
    squares.size // 4
    squares.lastIndex // 3
    squares[3] // 16
    squares[1] // 4
    squares[2] = 100 // squares: 1, 4, 100, 16

    /*
    Note that like in Java, an array variable itself stores a reference to actual data.
    For this reason, assigning array variables basically shares the same set
    of data between variables:
     */
    val numbers = squares
    numbers[0] = 1000 // squares = [1000, 4, 100, 16]

    // create a separate array
    val anotherSquares = squares.copyOf()
    anotherSquares[0] = 100000 // squares array is not affected
    val anotherSquares1 = squares.copyOf(5) // [1000, 4, 100, 16, 0]

    val arrayA = intArrayOf(1, 2, 3) + 4 // add single element: 1, 2, 3, 4
    val arrayB = intArrayOf(1, 2, 3) + intArrayOf(5, 6) // add another array: 1, 2, 3, 5, 6

    // Unlike strings, == and != operators on arrays compare references
    // rather than elements themselves:
    intArrayOf(1, 2, 3) == intArrayOf(1, 2, 3) // false

    // If you want to compare array content,
    // you should use the contentEquals() function:
    intArrayOf(1, 2, 3).contentEquals(intArrayOf(1, 2, 3)) // true

}