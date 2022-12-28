package kotlinInDepth.functions

/* Scope and visibility */

/* Kotlin functions can be broken down into three categories,
depending on where they’re defined:
* 1) top-level functions are declared directly in a file.
* 2) member functions are declared in some type. (postponed till Chapter 4)
* 3) local functions are declared inside another function.
 */

fun fun1(args: Array<String>) { // Top-level function, public by default

    fun fun2() /* Local function. Its scope is to the enclosing code block (line 22) */ {
        // able to access declarations available in enclosing functions, including their parameters:
        args[0] // OK
    }
    // Note: local functions and variables can not have any visibility modifiers.

    giveFive() // from 'FunctionAnatomy.kt' since top-level functions are public

    fun2() // OK
}

// fun2() // Error

/* Visibility modifiers */

private fun privateFun() {
    println("I am a private fun.")
    // accessible only in the containing file ('ScopeAndVisibility.kt' in our case)
}

internal fun internalFun() {
    println("I am a internal fun.")
    // accessible only in the containing module ('KotlinLearning' in our case)
}

fun main() {
    privateFun() // OK
    internalFun() // OK
}
