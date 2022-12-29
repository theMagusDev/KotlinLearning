package kotlinInDepth.nullability.elvisOperator

fun sayHelloOrNull(name: String?) {
    println("Hello, $name!")
}

fun programWithoutElvis() {
    sayHelloOrNull("Ivan") // Hello, Ivan!
    sayHelloOrNull(null) // Hello, null!
}

// Elvis operator allows you to provide some
// default value in place of null.
fun sayHello(name: String?) {
    println("Hello, ${name ?: "Unknown"}")
}

fun programWithElvis() {
    sayHello("Ivan") // Hello, Ivan!
    sayHello(null) // Hello, Unknown!
}

// In other words, the result of this operator is
// the left argument when it’s not null and the right one otherwise.

/* sayHello() code is equal to:
fun sayHello(name: String?) {
    println("Hello, " + (if (name != null) name else "Unknown"))
}
 */

/* main method */
fun main() {
    programWithoutElvis()
    programWithElvis()
}