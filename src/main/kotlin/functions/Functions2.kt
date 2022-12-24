package functions

import kotlin.math.PI

fun increment(number: Int /* Parameters are 'val' by default */): Int {
    // number += 1 // Error: Val cannot be reassigned

    // 'number' is a copy from the respective call argument (like in Java)
    return number
}

// But when the parameter is a reference,
// what gets copied is only the reference itself.

fun giveIncrementedFirstElement(array: IntArray): Int {
    array[0]++
    return array[0]
}

fun main() {
    val myArray = intArrayOf(1, 2, 3)
    println(giveIncrementedFirstElement(myArray)) // 2
    println(myArray.contentToString()) // [2, 2, 3]
}

fun circleArea(radius: Double): Double = PI * radius * radius // expression-body function
// return type can be omitted: fun circleArea(radius: Double) = PI * radius * radius

fun circleAreaLambda(radius: Double) = {PI * radius * radius} // lambda declaration
