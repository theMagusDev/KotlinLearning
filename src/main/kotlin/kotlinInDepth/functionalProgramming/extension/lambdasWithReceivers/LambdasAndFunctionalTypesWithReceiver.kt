package kotlinInDepth.functionalProgramming.extension.lambdasWithReceivers

// Similar to functions and properties, Kotlin allows you to use extension
// receivers for lambdas and anonymous functions. Such functional values are
// described by a special variety of functional types with the receiver.

// Its anatomy:

// val function: A.(B) -> C = { body }
//        ^      ^  ^     ^      ^
//       (1)    (2)(3)   (4)    (5)
/*
* 1 - functional value name;
* 2 - receiver object type, function can be called on it;
* 3 - object's parameter;
* 4 - return type;
* 5 - action to perform (function's body).
 */

// Let’s rewrite our aggregate() example to use a functional value with
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

fun aggregate(numbers: IntArray, operation: Int.(Int) -> Int): Int {
    // Second argument:
    // A function of type '(Int) -> Int' with receiver (i.e. function extending the Int type)
    // Name: "operation",
    // Receiver type: Int,
    // Parameter type: Int,
    // Return type: Int,
    // Body: Passed as an argument.

    var result = numbers.firstOrNull()
        ?: throw IllegalArgumentException("Empty array")
    for (number in numbers)
        result = result.operation(number)
    return result
}
fun sum(numbers: IntArray) =
    aggregate(numbers = numbers, operation = {number: Int -> this + number})
fun sum1(numbers: IntArray) =
    aggregate(numbers) { number: Int -> this + number }
// We are extending Int with lambda {number: Int -> this + number},
// that is why we can use 'this' expression there.

// In this case, any lambda passed as an argument gets an implicit receiver
// which we can access using 'this' expression: {number: Int -> this + number}


// Similarly, we can use an extension syntax for anonymous functions.
// The receiver type is specified just before function’s parameter list.
fun sum2(numbers: IntArray) = aggregate(
    numbers = numbers,
    operation = fun Int.(result: Int) = this + result
)

fun sum3(numbers: IntArray) = aggregate(numbers) {this + it}
// second argument is the extension function with receiver Int, so
// that is why here we can use 'this' expression.

// Unlike extension function definitions, a functional value with the receiver
// can be called as a non-extension function with the receiver placed before
// all succeeding arguments (it is what the compiler actually do when
// we create an extension function. See UnderTheHood.kt for more details).
// We could have written, for example:
fun aggregate3(numbers: IntArray, operation: Int.(Int) -> Int): Int {
    var result = numbers.firstOrNull()
        ?: throw IllegalArgumentException("Empty array")
    for (number in numbers)
        result = operation(result, number) // Non-extension call
    return result
}

// Basically, non-literal values of a functional type with the receiver
// are freely interchangeable with values of the corresponding type where
// the receiver is used as the first parameter as if they have the same type.
// This is possible because such values have essentially
// the same runtime representation:
val min1: Int.(Int) -> Int = { if (this < it) this else it }
val min2: (Int, Int) -> Int = min1
val min3: Int.(Int) -> Int = min2

// Note, however, that while it’s possible to invoke a functional value
// with the receiver as either an extension or non-extension (with the receiver
// placed as the first argument), functional values without receivers can be
// invoked using a non-extension syntax only:
fun main() {
    val min1: Int.(Int) -> Int = { if (this < it) this else it }
    val min2: (Int, Int) -> Int = min1
    println(3.min1(2)) // OK: calling min1 as extension
    println(min1(1, 2)) // OK: calling min1 as non-extension
    // println(3.min2(2)) // Error: can not call min2 as extension of Int
    println(min2(1, 2)) // OK: calling min2 as non-extension
}

// Lambdas with the receiver give you a powerful tool which can be used for
// building DSL-like API. We will discuss it later in more detail.