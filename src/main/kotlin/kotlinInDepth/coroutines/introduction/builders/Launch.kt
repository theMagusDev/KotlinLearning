package kotlinInDepth.coroutines.introduction.builders

import kotlinx.coroutines.*;
import java.lang.System.*;

// File #1

/*
The `launch()` function starts a coroutine and returns a Job object which you
can use to track its state and cancel the underlying task when needed. The
function takes a suspending lambda of type CoroutineScope.() -> Unit
which comprises the body of a new coroutine.
 */

fun main() {
    val time = currentTimeMillis()
    GlobalScope.launch {
        delay(100)
        println("Task 1 finished in ${currentTimeMillis() - time}ms")
    }
    GlobalScope.launch {
        delay(100)
        println("Task 2 finished in ${currentTimeMillis() - time}ms")
    }
    Thread.sleep(200)
    // Note: `GlobalScope` is a delicate API. It is easy to accidentally
    // create resource or memory leaks when GlobalScope is used.
}

/*
 Task 1 finished in 182ms
 Task 2 finished in 182ms
 */

// A thing worth noting is that both tasks complete at virtually the same
// time relative to the program start which means that they are really
// executed in parallel. A particular order is not guaranteed.

// Thread.sleep() is needed because by default coroutines are launched in
// daemon mode. So if non-daemon threads finish, program finishes too.

// Note that it is strongly discouraged to use Thread.sleep()
// inside suspending functions  because such code defeats the entire purpose of coroutines.

// The launch() builder is suited for cases when the concurrent task is not
// supposed to compute some result: that’s why it takes a Unit-typed lambda.