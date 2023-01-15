package hyperskill.scopeFunctions

// with() //

// When we use with, it sounds like:
// "With this object, do some work."

/* Features:

* Isn't an extension function.
* Context object is available as 'this'.
* Returns the result of a lambda.

Use when:

    1) First, when we want to do something with the context object and don't want
to receive a result. Remember — with returns the result of a lambda,
but according to Kotlin code conventions, we use this scope function when
we don't need a certain result. Truly, "What Happens in with, stays in with".
*/

fun program5() {
    val musicians = mutableListOf("Thom York", "Jonny Greenwood", "Colin Greenwood")
    with(musicians) {
        println("'with' is called with the argument $this")
        println("List contains ${this.size} elements")
    }
    // We print the needed data and don't try to get a certain result.
    // Result: Unit
}

/*
    2) Second, we use with() when we want to make an accessory object whose
parameters or functions may be used to calculate the result.
It is important — this new object is used as an ACCESSORY one
(we will work with the real object in run).
 */
fun program6() {
    val musicians = mutableListOf("Thom York", "Jonny Greenwood", "Colin Greenwood")
    val firstAndLast = with(musicians) {
        // 'musicians' is an accessory object. Can be called with 'this'
        return@with "First list element: ${this.first()}, last list element: ${this.last()}"
    }
}
