package kotlinInDepth.nullability.smartCasts

fun isLetterString(s: String?): Boolean {
    if (s == null) return false
    // s in non-nullable here
    if (s.isEmpty()) return false
/*      ^
Smart cast to kotlin.String
*/
    for (ch in s) {
        if (!ch.isLetter()) return false
    }
    return true
}

fun program1() {
    println(isLetterString(null)) // false
    println(isLetterString("")) // false
    println(isLetterString("Hello")) // true
}

/* Smart casts also work inside other statements such as when */
fun describeNumber(number: Int?) = when (number) {
    null -> "null"
    in 0..10 -> "small"
    in 11..100 -> "large"
    else -> "out of range"
}

// or right-hand sides of || and && operations:
fun isSingleChar(s: String?) = s != null && s.length == 1

// Note: in order to perform, a smart cast compiler has to ensure that
// the variable in question doesn’t change its value between
// the check and the usage.

// In particular, immutable local variables
// we’ve seen so far permit smart casts without limitations since
// they can’t change the value after initialization.
// Mutable variables, however, may prevent smart casts when
// modified between the null check and the usage:
fun inputLength() {
    var s = readLine() // String?
    if (s != null) {
        s = readLine() // String?
        // No smart cast has changed its value type
        // println(s.length) // Error
    }
}


