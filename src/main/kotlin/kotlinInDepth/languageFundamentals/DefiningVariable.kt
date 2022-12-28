package kotlinInDepth.languageFundamentals

fun main() {

    val a = readLine()!!.toInt()
    val b = readln().toInt() // the same
    println(a + b)

    // specify the type explicitly when necessary
    val n: Int = 100
    val text: String = "Hello!"

    val num: Int
    // println(num) // error: Variable 'num' must be initialized

    val (x, y) = "Hello Hi".split(" ")
    println(x) // Hello
    println(y) // Hi
}