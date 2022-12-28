package kotlinInDepth.loops

import kotlin.random.Random

fun main() {
    println("Calculate your sum: ")
    calculateSum()
    println("Guess a number: ")
    guessNumber()
}

fun calculateSum() {
    var sum = 0
    var num = 0
    // first execute 'do'
    do {
        num = readln().toInt()
        sum += num
    } while (num != 0) // then check. If true, repeat.
    println("Sum: $sum")
}

fun guessNumber() {
    val num = Random.nextInt(1, 101)
    var guess = 0
    while (guess != num) {
        guess = readln().toInt()
        if (guess < num)
            println("Too small")
        else if (guess > num)
            println("Too big")
    }
    println("Right: it's $num")
}