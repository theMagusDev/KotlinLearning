package kotlinInDepth.functionalProgramming.lambdasAndAnonymousFuns

import kotlinInDepth.functionalProgramming.higherOrderFunctions.aggregate

// How do we construct a particular value of a functional type?
// One way is to use a lambda expression which basically describes
// a function without giving it a name. Let’s, for example,
// define two more functions which compute the sum and maximum value
// using the aggregate() defined earlier:

fun sum(numbers: IntArray) =
    aggregate(numbers, {a, b -> a + b})
//                            ^
//                     lambda expression
fun max(numbers: IntArray) =
    aggregate(numbers, {a, b -> if (a > b) a else b})
fun program1() {
    println(sum(intArrayOf(1, 2, 3))) // 6
    println(max(intArrayOf(1, 2, 3))) // 3
}

/* Similarly to a function definition, lambda expression consists of:
* Parameter list: a, b
* A list of expressions or statements which have the lambda body: a + b
 */

// Unlike the function definition, you can’t specify a return type.
// It’s inferred automatically from the lambda body. Also, the last
// expression in the body is treated as a lambda result, so you don’t need
// to use an explicit return statement at the end.
fun sum1(numbers: IntArray) =
    aggregate(numbers, {a, b -> println("Hi!"); a + b})
//                                   ^            ^
//                         Some other code  Last expression (return)

// Note that the lambda parameter list is not enclosed in parentheses.
// Parentheses around lambda parameters are reserved for the so called
// destructuring declarations which we’ll cover later.

// When lambda is passed as the last argument, it can be placed
// outside parentheses. This is in fact the recommended code style.
fun recommendedSum(numbers: IntArray) =
    aggregate(numbers) {a, b -> a + b}

// When lambda has no arguments, an arrow symbol -> can be omitted:
fun measureExecutionTime(action: () -> Unit): Long {
    //                           ^
    //                      no parameters
    val start = System.nanoTime()
    action()
    return System.nanoTime() - start
}
val additionTime = measureExecutionTime { 5 + 5 } // 348800

// Kotlin also has a simplified syntax for lambdas with a single parameter.
// In such cases, we can omit both the parameter list and an arrow
// and refer to the parameter by the predefined name it:
fun program2() {

    fun check(s: String, condition: (Char) -> Boolean): Boolean {
        for (c in s) {
            if (!condition(c)) return false
        }
        return true
    }

    println(check("Hello") {c -> c.isLetter()}) // true
    println(check("Hello") {it.isLowerCase()}) // false
}

fun program3() {

    fun check(s: String, condition: (Int, Char) -> Boolean): Boolean {
        for (i in s.indices) {
            if (!condition(i, s[i])) return false
        }
        return true
    }

    println(check("Hello") {_, char -> char.isLetter()}) // true
    println(check("Hello") {i, c -> i == 0 || c.isLowerCase()}) // true
}

fun program4() {

    // Another way to specify a functional value is to use
    // an anonymous function:

    fun sum(numbers: IntArray) =
        aggregate(numbers, fun(a, b) = a + b)
/*
    An anonymous function has almost the same syntax as an ordinary
    function definition, albeit with a few differences:

    * An anonymous function doesn’t have a name, so the fun keyword
    is immediately followed by a parameter list.

    * Similar to lambdas, you can omit explicit specification of
    parameter types if they can be inferred from the context.

    * Unlike a function definition, an anonymous function is an expression,
    so it can be, for example, passed to a function as an argument or
    assigned to a variable (this is the parallel similar difference between
    object definitions and anonymous object expressions).
 */

    // Unlike lambdas, anonymous functions allow you to specify the return
    // type. In this regard, they follow the same rules as function definitions.
    // The return type is optional (and can be inferred) if a function has
    // an expression body, and must be explicit (unless it’s the Unit)
    // when using a block body.

    fun sum1(numbers: IntArray) =
        aggregate(numbers, fun(a, b): Int { return a + b })
}

// Lambdas Expressions are essentially
// anonymous functions that we can treat as values.

// Similar to local functions, lambdas and anonymous functions can access
// their closure, or variables defined in their containing declaration.
// In particular, they can change mutable variables from the outer scope.
fun forEach(array: IntArray, action: (Int) -> Unit) {
    for (number in array) {
        action(number)
    }
}

fun program5() {
    var sum = 0
    forEach(intArrayOf(1, 2, 3, 4)) {
        sum += it
    }
    println(sum) // 10
}

/* Main method */
fun main() {
    program1()
    program2()
    program3()
    program4()
    program5()
}