package kotlinInDepth.rangesProgressionsInOperation

/* Ranges */
val chars = 'a'..'h' // all characters from ‘a’ to ‘h’ (including 'h')
val twoDigits = 10..99 // all two-digit integers from 10 to 99 (including 99)
val zero2One = 0.0..1.0 // all floating-point numbers in the range[0.0; 1.0]
// Note: the type of 'chars' is 'CharRange', type of 'twoDigits' is 'IntRange'
/*
* Range and progression types are defined in the Kotlin standard library
* as a set of classes like IntRange, FloatRange,
* CharProgression, IntProgression, and so on.
 */

/* In operator */
fun main() {
    val num = readln().toInt()
    println(num in 10..99) // num >= 10 && num <= 99
    println(num !in 10..99) // !(num >= 10 && num <= 99)
    println("def" in "abc".."xyz") // true
    println("zzz" in "abc".."xyz") // false
    // Ranges produced by the .. operation are closed
    // which means they include both start and end points.

    // There is another operation which allows you
    // to create semi-closed ranges with excluded end points.
    // This operation is only available for integer types
    val newTwoDigits = 10 until 100 // [10; 100)

    // Note: built-in ranges are empty if their end point
    // is strictly less than the start one.
    println(5 in 5..5) // true
    println(5 in 5 until 5) // false
    println(5 in 10..1) // false

    /* Progression */
    println(5 in 10 downTo 1) // true
    println(5 in 1 downTo 10) // false: empty progression

    // specify a custom progression step:
    (1..10 step 3) // 1, 4, 7, 10
    (15 downTo 9 step 2) // 15, 13, 11, 9
    // Note: step must be positive. Use downTo to create a descending range.
    // (1..10 step -3) // Exception: java.lang.IllegalArgumentException

    1..12 step 3 // 1, 4, 7, 10

    val s = "Hello, world!"
    println(s.substring(1..4)) // ello
    println(s.substring(1 until 4)) // ell
    println(s.substring(1, 4)) // ell

    /* sliceArray */
    IntArray(10, {it * it}).sliceArray(2..5)
    // [4, 9, 16, 25]
    IntArray(10, {it * it}).sliceArray(2 until 5)
    // [4, 9, 16]

    /* Ranges are not the only types supporting the in/!in operation */
    val numbers = intArrayOf(3, 7, 2, 1)
    val text = "Hello!"
    println(2 in numbers) // true
    println(9 !in numbers) // true
    println(4 in numbers) // false
    println('a' in text) // false
    println('H' in text) // true
    println('h' !in text) // true

}