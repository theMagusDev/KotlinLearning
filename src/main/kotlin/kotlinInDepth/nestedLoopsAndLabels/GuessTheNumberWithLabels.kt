package kotlinInDepth.nestedLoopsAndLabels

import kotlin.random.Random
import kotlin.random.nextInt

fun main() {
    val num = Random.nextInt(1..100)
    loop@ while (true) {
        val guess = readln().toInt()
        val message = when {
            guess < num -> "Too small"
            guess > num -> "Too big"
            else -> break@loop
        }
        println(message)
    }
    println("Right: it's $num")
}