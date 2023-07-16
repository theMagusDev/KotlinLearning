package kotlinInDepth.generics.variance.declaration.`in`

// Part 2. First see 'out' package, then this

/*
The ‘in’ positions similarly cover the usages where values are consumed
like arguments of function calls and contravariant type arguments.

Similarly, we can use the in keyword to declare the type parameter
contravariant. This is possible when its generic type acts as a consumer, that
is, the type parameter itself has no usages in ‘in’ positions. For example:
 */

class Writer<in T> {
    fun write(value: T) {
        println(value)
    }
    fun writeList(values: Iterable<T>) {
        values.forEach { println(it) }
    }
}

fun main() {
    val numberWriter = Writer<Number>()

    // Correct: Writer<Number> can also handle Int
    val integerWriter: Writer<Int> = numberWriter
    integerWriter.write(100)
}
