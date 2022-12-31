package kotlinInDepth.basicStatements.conditionals

fun max(a: Int, b: Int): Int {
    if (a > b) return a
    else return b
}

fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        val message = "Hello, world!"
        println(message)
    } else {
        println()
    }
}