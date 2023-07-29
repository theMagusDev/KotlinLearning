package kotlinInDepth.coroutines.controlflow.cancellation

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
When a parent coroutine is cancelled, it automatically cancels the execution
of all its children and the process continues until all hierarchy is cancelled:
 */

fun main() {
    runBlocking {
        val parentJob = launch {
            println("Parent started")
            launch {
                println("Child 1 started")
                delay(500)
                println("Child 1 completed")
            }
            launch {
                println("Child 2 started")
                delay(500)
                println("Child 2 completed")
            }
            delay(500)
            println("Parent completed")
        }
        delay(100)
        parentJob.cancel()
    }
}

/*
Parent started
Child 1 started
Child 2 started

(100 ms)
Process finished with exit code 0
 */