package kotlinInDepth.coroutines.introduction.builders

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
The runBlocking() builder, on the other hand creates a coroutine
which by default executes in the current thread and blocks until it
completes. When coroutine returns successfully, the return value of the
suspend lambda becomes the value of the entire runBlocking() call. When
the coroutine is cancelled, runBlocking() throws an exception
 */

fun main() {
    GlobalScope.launch {
        delay(100)
        println("Background task: ${Thread.currentThread().name}")
    }
    runBlocking {
        println("Primary blocking task: ${Thread.currentThread().name}")
        delay(200)
    }
}

/*
 Primary blocking task: main
 Background task: DefaultDispatcher-worker-1
 */

/*
`runBlocking()` shouldn't be used inside other coroutines. It’s
intended as a kind of bridge between blocking and non-blocking
code and can be used, for example, as a top-level builder in
the main function or tests.
 */