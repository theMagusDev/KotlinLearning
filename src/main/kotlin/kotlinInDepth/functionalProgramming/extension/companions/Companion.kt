package kotlinInDepth.functionalProgramming.extension.companions

// Before we’ve introduced an idea of the companion object which is
// a special nested object whose members can be accessed by the name
// of its containing class. This useful feature covers extensions as well.

fun IntRange.Companion.singletonRange(n: Int) = n..n

fun program5() {
    println(IntRange.singletonRange(5)) // 5..5
    println(IntRange.Companion.singletonRange(3)) // 3..3
}

// The same idea also works for extension properties.
val String.Companion.HELLO
    get() = "Hello"

fun program6() {
    println(String.HELLO) // Hello
    println(String.Companion.HELLO) // Hello
}

// Note that the definition of extensions on the companion object
// is only possible if a class in question has explicit declaration
// of the companion even if it’s empty.

class Person(val name: String, val lastName: String) {
    companion object
}
val Person.Companion.UNKNOWN by lazy { Person("John", "Doe") }

// We can’t, on the other side, define an extension for the companion object
// of Any since it does not exist:

// fun Any.Companion.sayHello() = println("Hello")
// Error: Unresolved reference: Companion. Companion is undefined.

/* Main */
fun main() {
    program5()
}