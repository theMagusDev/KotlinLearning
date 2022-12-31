package kotlinInDepth.basicStatements.exception

/*
The throw expression has the type Nothing.
This type has no values and is used to mark
code locations that can never be reached.
In your own code, you can use Nothing to mark
a function that never returns:
 */
fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}

fun abc() {
    var name = null
    // name = "Set name"
    val s: String = name ?: fail("Name required")
    println(s) // 's' is known to be initialized at this point
}

val x = null // 'x' has type `Nothing?`
val l = listOf(null) // 'l' has type `List<Nothing?>`