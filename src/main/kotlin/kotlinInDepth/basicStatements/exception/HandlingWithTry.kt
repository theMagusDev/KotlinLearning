package kotlinInDepth.basicStatements.exception

fun readInt(default: Int): Int {
    try {
        return readln().toInt() // toInt() may throw an exception
    } catch (e: NumberFormatException) {
        return default
    }
}

fun main() {
    val a = readInt(-1)
    println("a = $a")
}