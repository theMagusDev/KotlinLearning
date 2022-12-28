package kotlinInDepth.exception

fun newReadInt1(default: Int) = try {
    readln().toInt() // return readln().toInt()
} catch (e: NumberFormatException) {
    default // return default
} finally {
    println("newReadInt1 function executed.")
}
// Note: finally block doesn’t affect the value of the
// entire try statement when it’s used as an expression.

fun main() {
    val a = newReadInt1(-1)
    println("a = $a")
}
