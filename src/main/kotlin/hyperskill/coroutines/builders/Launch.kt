package hyperskill.coroutines.builders

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
`launch` is similar to `runBlocking` in terms of syntax but has a different
purpose: it does not wait for the coroutine to finish but immediately
returns a special handler to the launched coroutine called a Job.
The coroutine itself continues working, but we can check the status or
even cancel it through a Job object.
 */

fun main1() {
    println("Starting")
    val downloadIDEA = GlobalScope.launch {
        downloadFileFromNet("IntelliJ IDEA Community Edition 2023.1.4.exe")
        println("IntelliJ IDEA downloaded successfully!")
    }
    val downloadPyCharm = GlobalScope.launch {
        downloadFileFromNet("PyCharm 2023.1.4.exe")
        println("PyCharm downloaded successfully!")
    }
    println("All coroutines were launched.")
}

/*
Starting
All coroutines were launched.
 */

// It's because coroutines behave like daemon threads. If non-daemon threads
// complete program will not wait for coroutines (daemon threads) and will finish.
// Wait for coroutines using [Job object].join() method:

fun main() {
    println("Starting")
    val downloadIDEA = GlobalScope.launch {
        downloadFileFromNet("IntelliJ IDEA Community Edition 2023.1.4.exe")
        println("IntelliJ IDEA downloaded successfully!")
    }
    val downloadPyCharm = GlobalScope.launch {
        downloadFileFromNet("PyCharm 2023.1.4.exe")
        delay(1000) // some delay
        println("PyCharm downloaded successfully!")
    }
    runBlocking { // block main Thread
        downloadIDEA.join() // wait for downloadIDEA coroutine
    }
    downloadPyCharm.cancel() // cancel PyCharm download if it hasn't been done yet.

    println("All coroutines were launched.")
}

/*
Starting
IntelliJ IDEA downloaded successfully!
All coroutines were launched.
 */