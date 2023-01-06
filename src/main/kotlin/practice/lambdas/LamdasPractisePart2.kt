package practice.lambdas

import kotlinInDepth.functionalProgramming.higherOrderFunctions.aggregate

// Task: write a functions which return the result of aggregate function. Use lambdas as parameters.
// Write sum, max, min and printingSum functions and print their result on intArrayOf(1, 2, 3)
fun program7() {
    fun sum(array: IntArray) = aggregate(array) {result, element -> result + element}
    fun max(array: IntArray) = aggregate(array) {maximum, element -> if (element > maximum) element else maximum}
    fun min(array: IntArray) = aggregate(array) {minimum, element -> if (element < minimum) element else minimum}
    fun printingSum(array: IntArray) = aggregate(array) {sum, element -> println("Summing $sum and $element. Current result = ${sum + element}"); sum + element}

    val myArray = intArrayOf(1, 2, 3)
    println(sum(myArray)) // 6
    println(max(myArray)) // 3
    println(min(myArray)) // 1
    println(printingSum(myArray))
    /* Output:
    Summing 1 and 2. Current result = 3
    Summing 3 and 3. Current result = 6
    6
     */
}

// Write a function checking some string on
// specific condition which is function type (Int, Char) -> Boolean
//                                             ^    ^
//                                           index char
// to check if char and index match some rule.
// This function should have Boolean return type.

fun program8() {
    fun check(string: String, condition: (Int, Char) -> Boolean): Boolean {
        for (i in string.indices) {
            if (!condition(i, string[i])) return false
        }
        return true
    }

    // Use it
    println(check("Hello") { _, char -> char.isLetter() }) // true

    // Check if the string starts with uppercase letter and don't have any other uppercase letters.
    println(check("Hello") {index, char -> char.isLowerCase() || index == 0}) // true
    println(check("Hello World") {index, char -> char.isLowerCase() || index == 0}) // false
}

// Rewrite sum() and max() functions using anonymous function instead of lambda
fun program9() {
    fun sum(array: IntArray) = aggregate(
        array,
        fun(summary, element) = summary + element
    )

    fun max(array: IntArray) = aggregate(
        array,
        fun(maximum, element): Int {
            return if (element > maximum) element else maximum
        }
    )

    val myArray = intArrayOf(1, 2, 3)
    println(sum(myArray)) // 6
    println(max(myArray)) // 3
}

// Create a forEach function accessing every IntArray element and
// performing some action on it (on every array element)
fun program10() {
    fun forEach(array: IntArray, action: (Int) -> Unit) {
        for (i in array.indices) {
            action(array[i])
        }
    }

    val myArray = intArrayOf(1, 2, 3)

    // calculate a sum using forEach()
    var sum = 0
    forEach(myArray) { sum += it }
    println(sum) // 6
}

fun main() {
    program7()
    program8()
    program9()
    program10()
}