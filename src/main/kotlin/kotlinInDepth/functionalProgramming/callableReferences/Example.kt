package kotlinInDepth.functionalProgramming.callableReferences

// In the previous section, we’ve seen how to construct a new functional value
// using lambdas and anonymous functions. But what if we already have a
// function definition and want to, for example, pass it as a functional value
// into some higher-order function? We can, of course, wrap it in a lambda
// expression like this:
fun check(s: String, condition: (Char) -> Boolean): Boolean {
    for (c in s) {
        if (!condition(c)) return false
    }
    return true
}
fun isCapitalLetter(c: Char) = c.isUpperCase() && c.isLetter()
// type: (Char) -> Boolean

fun program1() {
    println(check("Hello") { char: Char -> isCapitalLetter(char) }) // false
    println(check("ABC") { isCapitalLetter(it) }) // true
    //                                 ^
    //               providing the isCapitalLetter() function

    // In Kotlin, however, there is a much more concise way to use an existing
    // function definition as an expression of a functional type.
    // This is achieved through the use of callable references.


    /* Some theory */

    val isCapitalLetterFunVal: (Char) -> Boolean = ::isCapitalLetter
    // Compile-time:
    // When created, its type is KFunction1<Char, Boolean> (the subtype of kotlin.Function<R>).
    // It is the base type of all function references.
    // But later compiler upcasts it to kotlin.Function<R>.

    val isCapitalLetterFunVal2: (Char) -> Boolean = ::isCapitalLetter

    /* Java bytecode:
        ...
        KFunction isCapitalLetterFunVal = null.INSTANCE;
        Function1 isCapitalLetterFunVal2 = (Function1)null.INSTANCE;

        System.out.println( check("ABC", (Function1)isCapitalLetterFunVal) ); // line 64 of current file
        ...
     */

    // Note (not from the book): What exactly 'isCapitalLetterFunVal' is?
    // It is a functional value consisting of 2 parts:
    // 1) Type: (Char) -> Boolean
    // 2) Value: reference to the isCapitalLetter() function

    // Hence, when we:
    // 1) Run this function, we run its body. Its body is a link, so run
    // the linked function (isCapitalLetter() in our case)
    // 2) Print this function, we print its value. Its value is just a
    // reference to isCapitalLetter() function, so the output will be:
    // 'function isCapitalLetter (Kotlin reflection is not available)'
    // 3) Pass it instead of lambda expression, we pass in the
    // body of function it refers to.

    // So isCapitalLetterFunVal copies the isCapitalLetter()'s type
    // and have the link to this top-level function as its value.

    println(check("ABC", isCapitalLetterFunVal)) // true
    println(isCapitalLetterFunVal) // function isCapitalLetter (Kotlin reflection is not available)

    println(check("Hello", ::isCapitalLetter))
    //                              ^
    //             Use an existing function definition,
    //         i.e. the top-level function isCapitalLetter()
    //                  of type (Char) -> Boolean
    //     (this is the link to top-level isCapitalLetter() function)
}

fun sendEmail(name: String, message: String) =
    println("A message $message to $name was sent!")
fun program2() {
    val action1: (String, String) -> Unit = {name: String, message: String -> sendEmail(name, message)}
    action1("John", "Hello")
    /* Java bytecode:
        Function2 action1 = (Function2)null.INSTANCE;
        action1.invoke("John", "Hello");
     */

    val action2 = ::sendEmail // type: KFunction2<String, String, Unit>
    action2("John", "Bye")
    /* Java bytecode:
        KFunction action2 = null.INSTANCE;
        ((Function2)action2).invoke("John", "Bye");
     */
}

fun program3() {
    fun evalAtZero(function: (Int) -> Int) = function(0)
    fun inc(n: Int) = n + 1
    fun dec(n: Int) = n - 1

    println(evalAtZero(::inc)) // 1
    println(evalAtZero(::dec)) // -1
}

fun main() {
    program1()
}