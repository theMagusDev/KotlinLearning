package kotlinInDepth.functions

import kotlin.math.PI

fun main() {
    fun circleArea(radius: Double): Double {
        return PI * radius * radius
    }

    val myRadius = 5.3
    println(circleArea(myRadius)) // 88.24733763933727
    println(giveFive()) // 5
}

fun giveFive(): Int {
    return 5
    val x = 5 // No error, but warning: "Unreachable code"
}



