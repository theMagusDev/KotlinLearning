package hyperskill.coroutines.cancelJobs

import hyperskill.coroutines.stoppingScopedJobs.preCache
import kotlinx.coroutines.*

suspend fun doChildCancellingJob() = coroutineScope {
    launch { preCache("img_3.jpg") }
    for (i in 1..10) {
        println("Running long operation for ${i * 10}ms")
        delay((i * 10).toLong())
        if (i == 3) {
            this.coroutineContext.cancelChildren()
        }
    }
    println("doChildCancellingJob() finished")
}

suspend fun doSelfCancellingJob() = coroutineScope {
    launch { preCache("img_3.jpg") }
    for (i in 1..10) {
        println("Running long operation for ${i * 10}ms")
        delay(10)
        if (i == 5) {
            this.coroutineContext.cancel()
        }
    }
    println("doChildCancellingJob() finished")
}

fun main() {
    runBlocking { doChildCancellingJob() }
    /*
     Running long operation for 10ms
     Running long operation for 20ms
     Running long operation for 30ms
     Running long operation for 40ms
     Running long operation for 50ms
     Running long operation for 60ms
     Running long operation for 70ms
     Running long operation for 80ms
     Running long operation for 90ms
     Running long operation for 100ms
     doChildCancellingJob() finished
     */

    runBlocking { doSelfCancellingJob() }
    /*
     Running long operation for 10ms
     Running long operation for 20ms
     Running long operation for 30ms
     Running long operation for 40ms
     Running long operation for 50ms
     Running long operation for 60ms
     Exception in thread "main" kotlinx.coroutines.JobCancellationException: ScopeCoroutine was cancelled; job=ScopeCoroutine{Cancelled}@2ff4f00f
     */
}

// And it's 6 lines, not 5, because of the cooperative nature
// of coroutines: the loop will continue after the job has
// been "already" cancelled until the next suspending function call,
// which happens after println.