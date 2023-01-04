package kotlinInDepth.funcitonalProgramming.functionalType

import kotlinInDepth.funcitonalProgramming.higherOrderFunctions.program1
import kotlinInDepth.funcitonalProgramming.higherOrderFunctions.program2

/*
* The functional type describes values which can be used like functions.
*
* Syntactically, such a type is similar to a function signature and
contains the following two components:
* A list of parentheses-enclosed argument types which determine
which data can be passed to the functional value.
* A return type which determines the type of result returned
by the value of functional type.
 */

// Note that the return type must be always specified explicitly
// even if it’s the Unit.

// Example: (Int, Int) -> Boolean
// represents a function which takes a pair of integers
// as its input and returns a boolean values as a result.

// The value of the functional type can be invoked
// just like an ordinary function:
// op(result, numbers[i]).

// An alternative way is to use an invoke() method
// which takes the same arguments:
// result = op.invoke(result, numbers[i])

// Since version 1.4, Kotlin supports explicit conversion between
// Kotlin SAM interfaces and functional types. For this to work,
// you need to accompany interface declaration with the fun keyword:

fun interface IntOp { // SAM interface (single abstract method, like in Java)
    fun op(value: Int): Int
}
interface NotQuiteIntOp { // Ordinary interface: no ‘fun’ keyword
    fun op(value: Int): Int
}

fun program3() {
    val square: IntOp = IntOp { value -> value * value} // OK
    // val cube: NotQuiteIntOp = NotQuiteIntOp { value -> value * value * value} // Error
}

// The parameter list may be empty if function represented
// by a functional type does not take any parameters:
fun measureExecutionTime(action: () -> Unit): Long {
    val start = System.nanoTime()
    action.invoke() // same as action()
    return System.nanoTime() - start
}
fun program4() {
    println(measureExecutionTime() { println() }) // 743200 (nanoseconds)
    // you can omit parentheses from function call with lambda:
    println(measureExecutionTime { println() }) // 743200 (nanoseconds)
}

// Note that parentheses around parameter types are mandatory
// even if the function type has a single parameter or none at all:
val inc: (Int) -> Int = { n -> n + 1 } // OK
// val dec: Int -> Int = { n -> n - 1} // Error

// Values of functional types are not limited to function parameters.
// In fact, they may be used on equal terms with any other type.
// For example, you can store the functional value in a variable:
fun program5() {
    val lessThan: (Int, Int) -> Boolean = { a, b -> a < b}
    println(lessThan(1, 2)) // true

    // Note that if you omit a variable type, the compiler won’t
    // have enough information to infer types of lambda parameters:
    // val lessThan1 = {a, b -> a < b} // Error

    // In such a case, you’ll have to specify parameter types explicitly:
    val lessThan1 = {a: Int, b: Int -> a < b} // OK
}

// Just like any other type, a functional type may be nullable.
// In this case, we enclose the original type in parentheses
// before adding a question mark:
fun measureTime1(action: (() -> Unit)?): Long {
    val start = System.nanoTime()
    action?.invoke()
    return System.nanoTime() - start
}

fun program6() {
    println(measureTime1(null)) // 500 (nanoseconds)
}

// Functional types may be nested in which case they
// represent higher-order functions themselves:
fun program7() {
    val shifter: (Int) -> (Int) -> Int = {n -> { i -> i + n } }
    // with parentheses: (Int) -> ((Int) -> Int) = {n -> { i -> i + n } }
    val inc: (Int) -> Int = shifter(1)
    // now inc looks like: { i -> i + 1 }
    val dec = shifter(-1) // omit the value type
    // now dec looks like: { i -> i  - 1 }
    println(inc(5)) // 6
    println(dec(5)) // 4

    // If we want it to mean the function which takes an Int-to-Int
    // function and returns an Int, we have to use parentheses:
    val evalAtZero: ((Int) -> Int) -> Int = { f -> f(0) }
    // input: f - function. Return: f(0) - execute this function with arg 0

    println(evalAtZero { n -> n + 1 }) // 1
    // Execute function { n -> n + 1 } with argument 0. Result: 0 + 1 = 1

    println(evalAtZero { n -> n - 1 }) // -1
}
// A functional type may include optional names for its parameters.
// They can be used for documentation purpose to clarify the
// meaning of a functional value represented by this type:
fun aggregate1(
    numbers: IntArray,
    op: (resultSoFar: Int, nextValue: Int) -> Int
) {/*...*/}

// IDE Tip: press Ctrl + P while inside the function's arguments
// to see the necessary parameters.

/* Main method */
fun main() {
    program1()
    program2()
    program3()
    program4()
    program5()
    program6()
    program7()

}
