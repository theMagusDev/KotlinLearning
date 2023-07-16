package kotlinInDepth.annotations.define.deprecated.declare

@Deprecated(message = "Use readInt() instead")
fun readNum() = readln().toInt()

fun readInt(radix: Int = 10) = readln().toInt(radix)

fun main() {
    val a = readNum()
    val b = readNum()
    println(a + b)
}

// See 'Deprecated.jpg'
