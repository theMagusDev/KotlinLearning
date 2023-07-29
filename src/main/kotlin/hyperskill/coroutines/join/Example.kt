package hyperskill.coroutines.join

import kotlinx.coroutines.*
import kotlin.io.println

suspend fun foo() {
    println("Long computations...")
    delay(1500)
    println("Completed!")
}

suspend fun main() {
    coroutineScope {
        println("Main starts")
        val job = launch { foo() }
        job.join() // wait for foo()
        println("Main finishes")
    }

    // join all functions regardless of their order

    coroutineScope {
        println("Main starts")
        val job1 = launch { foo() }
        val job2 = launch { foo() }
        joinAll(job1, job2)
        println("Main finishes")
    }
}

/*
 Main starts
 Long computations...
 Completed!
 Main finishes
 */