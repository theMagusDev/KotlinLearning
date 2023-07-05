package kotlinInDepth.nullability.notNullAssertionOperator

import java.util.*

fun example() {
    val a = readlnOrNull() // String?
    val b = readlnOrNull()!! // String
    val c = readlnOrNull() as String // String
    // !! casts Nullable to Non-nullable
}

/*
In general, this operation should be avoided because null values usually
require some reasonable response instead of simply throwing an exception.
Sometimes, though, its usage is justified.
 */
fun main() {
    var name: String? = null
    fun initialize() {
        name = "John"
    }
    fun sayHello() {
        println(name!!.uppercase(Locale.getDefault()))
    }
    initialize()
    sayHello()
}