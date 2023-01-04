package hyperskill.lambdas.functionsAsObjects

/* In Kotlin, functions are First-class citizens, which means that they can:
* can be stored as variables;
* can be returned by a function;
* can be passed to a function as an argument;
* don't depend on their name;
* can be created at the program runtime.
 */

// Function types
// (parameters' types) -> return value type

fun sum(a: Int, b: Int): Int = a + b // (Int, Int) -> Int
fun sayHello() { println("Hello") } // () -> Unit

// Also, Kotlin allows getting references to functions.
// To get a reference to a top-level function, we simply need
// to write double colon (::) before its name, and
// we don't write parentheses and arguments after the name.
val sumObject: (Int, Int) -> Int = ::sum
fun program1() {
    sumObject(10, 20)
    // invoke the object sumObject. Oh, it is just a reference.
    // Ok, so invoke the function it references to, i.e. the sum()
    // passing in the arguments '10' and '20'
}

// Since a function can be stored as an object,
// why not create a function that returns such an object?
fun getRealGrade(x: Double) = x // (Double) -> Double
fun getGradeWithPenalty(x: Double) = x - 1 // (Double) -> Double

fun getScoringFunction(isCheater: Boolean): (Double) -> Double {
    return if (isCheater) {
        ::getGradeWithPenalty
    }
    else {
        ::getRealGrade
    }
}

fun program2() {
    // Clever student
    var wantedFunction = getScoringFunction(isCheater = false)
    wantedFunction(5.0) // 5.0

    // Cheater
    wantedFunction = getScoringFunction(isCheater = true)
    wantedFunction(4.0) // 3.0
}

fun applyAndSum(a: Int, b: Int, operation: (Int) -> Int): Int {
    return operation(a) + operation(b)
}
fun same(x: Int) = x
fun square(x: Int) = x * x
fun triple(x: Int) = x * 3

fun program3() {
    // Write in 3 ways: lambda with names, with 'it' and the function reference
    applyAndSum(1, 2) {x -> x}
    applyAndSum(1, 2) {it}
    applyAndSum(1, 2, ::same)
    // result: 3 = 1 + 2

    applyAndSum(1, 2) {x -> x * x}
    applyAndSum(1, 2) {it * it}
    applyAndSum(1, 2, ::square)
    // result: 5 = 1 * 1 + 2 * 2

    applyAndSum(1, 2) {x -> x * 3}
    applyAndSum(1, 2) {it * 3}
    applyAndSum(1, 2, ::triple)
    // result: 9 = 1 * 3 + 2 * 3
}

// Real-world usage
fun realProgram() {
    fun isNotDot(c: Char): Boolean = c != '.'
    val originalText = "I don't know... what to say..."

    // remove all the dots in originalText
    // using the filter method with lambda and fun ref

    val textWithoutDots1 = originalText.filter() { c: Char -> isNotDot(c) }
    //                                        ^^
    // Remember: parentheses from function call with lambda can be omitted:
    val textWithoutDots2 = originalText.filter { isNotDot(it) }
    val textWithoutDots3 = originalText.filter (::isNotDot)
}

fun main() {
    program1()
    program2()
    program3()
    realProgram()
}