package kotlinInDepth.exception

import java.lang.IllegalArgumentException
import kotlin.NumberFormatException

fun parseIntNumber(s: String): Int {
    var num = 0
    if (s.length !in 1..31)
        throw NumberFormatException("Length of '$s' is ${s.length}, must be from 1 to 31.")
    for (c in s) {
        if (c !in '0'..'1')
            throw NumberFormatException("Not a binary number: $s")
        num = num * 2 + (c - '0')
    }
    return num
}

/*
Java vs Kotlin: Unlike Java, creating a class instance (in this case, it’s an
exception) doesn’t require any special keywords like Java’s new. In Kotlin,
a constructor invocation NumberFormatException(“Not a number: $s”)
looks like an ordinary function call.
 */

/*
Java vs Kotlin: In Kotlin, throw is an expression of type Nothing like
break and continue we’ve seen in one of the earlier sections. For example:
 */
fun sayHello(name: String) {
    val message =
        if (name.isNotEmpty()) "Hello, $name" // message: String
        else throw IllegalArgumentException("Empty name") // message: Nothing
    println(message)
}