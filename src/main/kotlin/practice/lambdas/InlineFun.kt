package practice.lambdas

fun sum(a: Int, b: Int) = a + b
inline fun aggregateIntArray(array: IntArray, action: (Int) -> Int): IntArray {
    for (i in array.indices) {
        array[i] = action(array[i])
    }
    return array
}
fun main() {
    val myArray = intArrayOf(1, 2, 3)
    println((aggregateIntArray(myArray) {it * 2}).contentToString())
}
