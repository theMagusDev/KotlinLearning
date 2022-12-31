package kotlinInDepth.properties.topLevel

// Similar to classes or functions, properties may be declared
// at the top-level. In this case, they serve as a sort of
// global variables or constants.

val prefix = "Hello" // top-level immutable property. Can be used anywhere.
fun main() {
    val name = readLine() ?: return
    println("$prefix $name")
}