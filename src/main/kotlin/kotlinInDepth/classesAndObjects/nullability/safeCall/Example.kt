package kotlinInDepth.nullability.safeCall

/* Let’s consider one of our earlier examples:

fun readInt() = readlnOrNull!!.toInt()

This function works fine as long as your program uses the console
as its standard I/O. If, however, we’ve started the program
piping some file as the standard input, it could’ve failed
with KotlinNullPointerException if this file was empty.
Using the safe call operator, we can rewrite it:
*/

fun readInt(): Int? = readlnOrNull()?.toInt()
/* is equal to the code:
fun readInt(): Int? {
    val tmp = readlnOrNull()
    return if (tmp != null) tmp.toInt() else null
}
 */
fun main() {
    println(readInt())
    // Input: 75, Output: 75

    // Input: null, Output: null

    // Input: 1hi, Output: NumberFormatException

    // Why exception? Because safe call only clarifies, that the value
    // is non-null, otherwise it gives null to the assignment. But
    // 1hi is not null, so .toInt() applies to it, and this is the
    // reason of NumberFormatException.

    // In other words, the safe call operator behaves like an
    // ordinary call when its receiver (left-hand operand) is not null.
    // When its receiver is null, however,
    // it doesn't perform any calls and simply returns null.

    // Similar to || and && operations, safe calls follow
    // a lazy semantics. They do not evaluate call
    // arguments if the receiver is null.

    // Note that since the safe call operator may return null,
    // its type is always the nullable version
    // of the corresponding non-safe call.
    val n = readInt() // Int?
    if (n != null)
        println(n + 1)
    else
        println("No value")
}
