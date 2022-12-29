package kotlinInDepth.nullability.nullableTypes

/* Non-nullable */
fun isLetterString(s: String): Boolean {
    if (s.isEmpty()) return false
    for (ch in s) {
        if (!ch.isLetter()) return false
    }
    return true
}

fun programWithoutNullable() {
    println(isLetterString("abc")) // OK. Output: true
    // println(isLetterString(null)) Error
}

/* Nullable */
fun isBooleanString(s: String?) = s == "false" || s == "true"

fun programWithNullable() {
    println(isBooleanString("false")) // OK. Output: true
    println(isBooleanString(null)) // OK. Output: false

    // Note: every nullable type is a supertype of its base type
    // which enlarges its original set of values by including null.

    val s: String? = "abc" // OK
    // val s1: String = s // Error
}

// Interesting fact: At runtime, non-nullable values
// do not actually differ from the nullable ones.
// The distinction exists on the compilation level only.

/* main method */
fun main() {
    programWithoutNullable()
    programWithNullable()
}