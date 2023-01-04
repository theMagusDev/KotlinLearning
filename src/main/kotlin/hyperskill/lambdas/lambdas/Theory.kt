package hyperskill.lambdas.lambdas

import hyperskill.lambdas.functionsAsObjects.sum

// Note that if you want to declare a lambda without arguments,
// you do not need to write the "arrow symbols".

fun measureExeTime(action: () -> Unit): Long {
    val start = System.nanoTime()
    action()
    return System.nanoTime() - start
}

fun program1(){
    measureExeTime { println() } // no need in '->' here
}

// how to use an anonymous fun? Just store it in variable.
val mult = { a: Int, b: Int -> a * b }

fun program2(){
    println(mult(2, 3)) // 6
    // Also, you can pass such a function as an argument
    // or return such a function from another function.
}

//  Syntactic sugar

val originalText = "I don't know... what to say..."

fun isNotDot(c: Char): Boolean = c != '.'
val textWithoutDots1 = originalText.filter(::isNotDot)
val textWithoutDots2 = originalText.filter({char: Char -> char != '.' })
val textWithoutDots3 = originalText.filter() {char -> char != '.' }
val textWithoutDots4 = originalText.filter {c -> c != '.' }
val textWithoutDots5 = originalText.filter {it != '.'}

// Complex lambdas
val textWithoutSmallDigits = originalText.filter {
    if (!it.isDigit()) {
        return@filter true
    }
    val stringRepresentation = it.toString()

    stringRepresentation.toInt() >= 5
}

// Capturing variables
fun placeArgument(value: Int, f: (Int, Int) -> Int): (Int) -> Int {
    return { i -> f(value, i) }
    // The placeArgument transforms the f function that
    // takes two arguments to a function that takes a single argument.
}

fun program3() {
    val increment = placeArgument(1, ::sum)
    val increment1 = placeArgument(1, fun(a: Int, b: Int) = a + b)
    val increment2 = placeArgument(1) { a: Int, b: Int -> a + b }
    // increment2 = { 1, b: Int -> 1 + b }
    // function f replaces first argument with the given value.

    val triple = placeArgument(3) { a: Int, b: Int -> a * b }
    val triple1 = placeArgument(3, mult)

    println(increment(4)) // 5
    println(triple(4)) // 12
}