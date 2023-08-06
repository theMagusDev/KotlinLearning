package kotlinInDepth.functionalProgramming.functionReference

val dec: (Int) -> Int = Int::dec // reference to the dec() fun of Int class
val dec1: (Int) -> Int = {x -> x - 1}
fun main() {
    println(dec(4)) // 3
    println(dec1(4)) // 3
}