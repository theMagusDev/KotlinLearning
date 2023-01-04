package hyperskill.lambdas.functionReference

// A quick reminder: a function reference is a special link
// that refers to a certain function by its name and can be called
// at any time whenever we need it.

fun isOdd(x: Int) = x % 2 != 0

fun isEven(x: Int) = x % 2 == 0

fun printNumbers(numbers: MutableList<Int>, filter: (Int) -> Boolean) {
    for (number in numbers) {
        if (filter(number))
            print("$number ")
    }
}

fun main() {
    val numbers = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val oddFunction = ::isOdd
    println("Odd numbers: ${printNumbers(numbers, oddFunction)}") // 1, 3, 5, 7, 9
    println("Even numbers: ${printNumbers(numbers, ::isEven)}")// 2, 4, ,6, 8, 10
}