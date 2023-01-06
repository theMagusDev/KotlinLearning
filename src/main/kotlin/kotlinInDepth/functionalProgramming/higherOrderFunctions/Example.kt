package kotlinInDepth.functionalProgramming.higherOrderFunctions

// Suppose that we want to define a function which computes
// a sum of elements in an integer array.

fun oldSum(numbers: IntArray): Int {
    var result = numbers.firstOrNull()
        ?: throw IllegalArgumentException("Empty array")
    for (i in 1..numbers.lastIndex) {
        result += numbers[i]
    }
    return result
}

fun program1() {
    println(sum(intArrayOf(1, 2, 3))) // 6
}

// What if we want to generify this function to cover other
// kinds of aggregates like a product or min/max value?
// We can keep the basic iteration logic in the function itself
// and extract the computation of intermediate values into a
// functional parameter which can be supplied at the call site.
fun aggregate(numbers: IntArray, operation: (result: Int, element: Int) -> Int): Int {
    var result = numbers.firstOrNull()
        ?: throw IllegalArgumentException("Empty array")
    for (i in 1..numbers.lastIndex) {
        result = operation(result, numbers[i])
    }
    return result
}
fun sum(numbers: IntArray) =
    aggregate(numbers, { result, element -> result + element})
/* Executed code:
fun aggregate(numbers: IntArray, operation: (Int, Int) -> Int): Int {
    var result = numbers.firstOrNull()
        ?: throw IllegalArgumentException("Empty array")
    for (i in 1..numbers.lastIndex) {
        result = result + element
        // instead of result = operation(result, numbers[i])
    }
    return result
}
 */
fun max(numbers: IntArray) =
    aggregate(numbers, {result, element -> if (element > result) element else result})

/* Executed code:
fun aggregate(numbers: IntArray, operation: (Int, Int) -> Int): Int {
    var result = numbers.firstOrNull()
        ?: throw IllegalArgumentException("Empty array")
    for (i in 1..numbers.lastIndex) {
        result = if (element > result) element else result
        // instead of result = operation(result, numbers[i])
    }
    return result
}
 */

fun program2() {
    println(sum(intArrayOf(1, 2, 3))) // 6
    println(max(intArrayOf(1, 2, 3))) // 3
}

// 'operation' parameter has a functional type (Int, Int) -> Int

// In our example, the op parameter can accept functional values
// which accept a pair of Int values and return some Int values
// as their result.

// At the call site in the sum() and max() functions, we pass a lambda
// expression which denotes such a functional value. It’s basically
// a definition of a local function without a name which uses a kind of
// simplified syntax. For example, in the following expression:
// { result, element -> result + element }
// result and element play the role of function parameters while the expression
// after -> computes the result. No explicit return statement is necessary in
// this case, and parameter types are inferred automatically from the context.

/* Main method */
fun main() {
    program1()
    program2()
}