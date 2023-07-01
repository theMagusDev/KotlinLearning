package kotlinInDepth.nullability.nullableTypes

// Nullable types don’t retain methods and properties
// available for their base types. The reason is that
// usual operations such as calling a member function
// or reading a property don’t make sense for the null value.

/*
fun isLetterString(s: String?): Boolean {
    if (s.isEmpty()) return false
    // Error: isEmpty() is not available on String?

    for (ch in s) {
    // Error: iterator() is not available on String?
        if (!ch.isLetter()) return false
    }
    return true
}
*/

// Note that you can’t use the for loop to iterate over
// the nullable String since String? doesn’t have an iterator() method.

// In fact, nullable types may have their own methods and properties
// thanks to the Kotlin extension mechanism. In Chapter 5 we will discuss
// this in detail.

//  One example is a string concatenation which
//  also works for values of String? type:
fun exclaim(s: String?) {
    println(s + "!")
}
fun main() {
    exclaim(null) // null!
    // null also can be printed:
    println(null) // null
}