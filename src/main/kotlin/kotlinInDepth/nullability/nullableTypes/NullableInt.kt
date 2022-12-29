package kotlinInDepth.nullability.nullableTypes

import practice.Employee

// Primitive types like Int or Boolean also have nullable versions.
// Bear in mind, though, that such types always represent boxed values:

fun main() {
    val n: Int = 1 // primitive value
    val x: Int? = 1 // reference to a boxed value

    // Interesting fact: The smallest nullable type is Nothing?
    // which does not contain any other value apart from the null constant.
    val a: Nothing? = null
    // Remember, that Nothing? is a type of the null itself
    // and a subtype of any other nullable type.

    // The largest nullable type Any? is the largest type
    // in the whole Kotlin type system and is considered a supertype
    // of any other type, nullable or not.
    val c: Any? = Employee() // can be anything
    val b: Any? = null // even null
}
