package kotlinInDepth.coroutines.controlflow.cancellation

import kotlinx.coroutines.*

/*
Jobs can be cancelled by calling their cancel() method. This provides a
standard mechanism for terminating computations which are no longer
necessary. The cancellation is cooperative; in other words, a cancellable
coroutine itself must check whether its cancellation is requested and
respond appropriately:
 */

suspend fun program1() {
    coroutineScope { // this: CoroutineScope
        // define some child job:
        val squarePrinter = this.launch(context = Dispatchers.Default) {
            var i = 1
            while (true) {
                println(i++)
            }
        }
        delay(100) // let child job run for some time
        squarePrinter.cancel() // cancel child job

        // here numbers are still printed, because `cancel()` just requested
        // `squarePrinter` to stop, but now ordered. It doesn't cooperate in cancellation.
    }
}

/*
One way to fix this is to repeatedly check whether the coroutine was
cancelled before doing the next piece of work:
 */

suspend fun program2() {
    val job = GlobalScope.launch {
        val squarePrinter = launch(Dispatchers.Default) {
            var i = 1
            while (isActive) {
                println(i++)
            }
        }
    }
    delay(100)
    job.cancel() // here child coroutine stops its execution
}

/*
 Another solution is to replace the state check with a call to some
suspending function which can respond to cancellation by throwing
`CancellationException`. This exception is used internally by the coroutine
library as a control-flow token signalling that job cancelling is in progress:
 */

suspend fun program3() {
    val squarePrinter = CoroutineScope(Dispatchers.Default).launch {
        var i = 1
        while (true) {
            yield() // Yields (уступает) the thread of the current coroutine dispatcher to other coroutines on the same dispatcher to run if possible.
            /*
             yield() calls `context.ensureActive()` function from Job.kt:

              public fun Job.ensureActive(): Unit {
                 if (!isActive) throw getCancellationException()
              }

             */
            println(i++)
        }
    }
    delay(100)
    squarePrinter.cancel()
}

suspend fun main() {
    program3()
}
