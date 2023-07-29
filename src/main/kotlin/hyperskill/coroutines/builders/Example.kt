package hyperskill.coroutines.builders

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/*
runBlocking is a regular function that takes exactly one suspending
function, executes it, waits until it finishes, and returns its result.
So we are waiting for the coroutine to finish.
 */

suspend fun downloadFileFromNet(file: String) = delay(5000)

fun main() {
    println("Starting downloading a file...")
    runBlocking(
        block = {
            println("Starting coroutine")
            downloadFileFromNet("IntelliJ IDEA Community Edition 2023.1.4.exe")
        }
    )
    println("File was successfully downloaded!")
}

// We can also use more than one suspend function:

fun program() {
    println("Starting downloading the files...")
    runBlocking(
        block = {
            println("Starting coroutine")
            downloadFileFromNet("Intellij IDEA Community Edition 2023.1.4.exe")
            downloadFileFromNet("PyCharm 2023.1.4.exe") // waits for previous function
        }
    )
    println("Files were successfully downloaded!")
}

/*
Does it work faster than regular code? No! Suspending functions are executed
sequentially, and we're waiting for the whole coroutine to finish.
 */