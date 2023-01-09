package practice.extensions

// Rewrite our aggregate() example to use a functional value with
// the receiver instead of a two-argument function.

/* Our old implementation:

fun aggregate(numbers: IntArray, operation: (result: Int, element: Int) -> Int): Int {
    var result = numbers.firstOrNull()
        ?: throw IllegalArgumentException("Empty array")
    for (i in 1..numbers.lastIndex) {
        result = operation(result, numbers[i])
    }
    return result
}

 */

// Write your new implementation below:

fun aggregate(numbers: IntArray, operation: Int.(Int) -> Int): Int {
    var result = numbers.firstOrNull()
        ?: throw IllegalArgumentException("Empty array")
    for (i in 1..numbers.lastIndex)
        result = result.operation(numbers[i])
    return result
}

// Create a sum() function using this aggregate() fun.

fun sum(array: IntArray) = aggregate(array) { this + it }

// Create a max() function using this aggregate() fun.

fun max(array: IntArray) = aggregate(array) { if (this > it) this else it }

fun main() {
    println(sum(intArrayOf(1, 2, 3))) // 6
    println(max(intArrayOf(1, 2, 3))) // 3
}