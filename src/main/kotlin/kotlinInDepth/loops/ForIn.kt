package kotlinInDepth.loops

fun main() {
    val myArray = IntArray(5) { it * it } // 0, 1, 4, 9, 16
    println("Sum: ${sumArray(myArray)}") // 30
    println(parseBinaryToInt("111")) // 7
    detailedArray(myArray)
    // The element at 0 is 0
    // ...
    // The element at 4 is 16
}

fun sumArray(array: IntArray): Int {
    var sum = 0
    for (element /*: Int*/ in array) {
        sum += element
        // element = 5 Error: you can not change iteration variable
    }
    return sum
}

// You can use the withIndex library function:
fun detailedArray(array: IntArray) {
    for ((index, value) in array.withIndex()) {
        println("The element at $index is $value")
    }
}

fun parseBinaryToInt(binaryStr: String, fallback: Int = -1): Int {
    var num = 0
    if (binaryStr.length !in 1..31) return fallback
    for (c in binaryStr) {
        if (c !in '0'..'1') return fallback
        num = num * 2 + (c - '0')
    }
    return num
}

