package kotlinInDepth.conditionals

fun main() {
    // val s = readln()
    val s = "15/2"
    val i = s.indexOf("/")
    // Split line like 10/3 into 10 and 3 and perform the division
    val result: String = if (i >= 0) {
        val a = s.substring(0, i).toInt()
        val b = s.substring(i + 1).toInt()
        "Result: ${a/b}"
    } else "Error: no '/' in input."

    println(result) // Result: 7

    // Note: when if is used as an expression both branches must be present.
}