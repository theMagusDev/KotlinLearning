package kotlinInDepth.exception

// Kotlin’s try can be used as an expression
fun newReadInt(default: Int) = try {
    readln().toInt() // return readln().toInt()
} catch (e: NumberFormatException) {
    default // return default
}