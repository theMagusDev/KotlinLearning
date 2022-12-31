package kotlinInDepth.basicStatements.loops

import kotlin.random.Random

fun guess() {
    val num = Random.nextInt(1, 101)
    while (true) {
        val guess = readln().toInt()
        val message =
            if (guess < num) "Too small"
            else if (guess > num) "Too big"
            else break
        println(message)
    }
    println("Right: it's $num")
}
// However, in more complex expressions,
// it might in fact hinder the understanding of your code

/* Continue statement */

/*
* Suppose that we want to count a number of times
* each English letter occurs in a given string.
*/
fun countLetters(text: String): IntArray {
    val counts = IntArray(size = 'z' - 'a' + 1)
    for (char in text) {
        val charLower = char.lowercaseChar()
        if (charLower !in 'a'..'z') continue
        counts[charLower - 'a']++
    }
    return counts
}

/*
Java vs Kotlin: In Java, a break is also used to stop the execution of the
remaining branches in a switch statement. Since when expressions don’t
follow a fall-through semantics, break statements do not serve the same
purpose in Kotlin.
In Kotlin, using simple break or continue expressions
out of loop inside 'when' is prohibited
 */


