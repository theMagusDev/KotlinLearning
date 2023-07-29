package hyperskill.coroutines.contextAndDispatchers

import kotlinx.coroutines.*

// File #2

/*
 Dispatcher is just an interface and we can implement our own, if needed.
In fact, most platforms like JVM/Android have their own specific dispatchers.
There are three standard dispatchers:
 1) Dispatchers.Default – this dispatcher will be used if none is
specified explicitly. It schedules coroutine execution in
a background thread from a shared thread pool. It's a good
choice for all types of computations that consume CPU.
 2) Dispatchers.IO – it uses another shared thread pool and is
designed to handle I/O load with a lot of operations that can block
on waiting for data. Use it for disk read/write and network requests.
 3) Dispatchers.Unconfined — it starts execution of a coroutine
in the current thread and runs until the first suspension; after that,
it resumes in a different suitable thread. Normally, this dispatcher
should not be used.

 In case we need to use our own pool of one or multiple threads, there
are helper functions `newSingleThreadContext()` and `newFixedThreadPoolContext()`,
which take care of creating a thread pool, and the dispatcher that
uses it for execution. They may be handy if we need to run a fixed
number of long-running background jobs.

 However, to simply limit the number of parallel threads allowed
for execution, coroutines v1.6 offer a better option — `limitedParallelism()`.
This function can be applied to any multi-threaded dispatcher to guarantee
that no more than N coroutines will run in parallel. For instance,
Dispatchers.IO.limitedParallelism(2) uses the same shared IO pool
but allows to use two threads only.
 */

// For example, it can be useful if we are allowed to open only
// a limited number of DB connections:

fun main() {
    // runBlocking() return Job object
    val job = runBlocking {
        launch { // context of the parent, main runBlocking coroutine
            println("main: ${Thread.currentThread()}")
        }
        launch(context = Dispatchers.Unconfined, block = { // will work on main thread, then on another one
            println("Unconfined A: ${Thread.currentThread()}")
            delay(1) // suspenstion point
            println("Unconfined B: ${Thread.currentThread()}")
        })
        launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
            println("Default: ${Thread.currentThread()}")
        }
        launch(newSingleThreadContext(name = "MyThread1")) { // will get its own new thread
            println("New MyThread1: ${Thread.currentThread()}")
        }
        launch(newSingleThreadContext(name = "MyThread2")) { // will get a different new thread
            println("New MyThread2: ${Thread.currentThread()}")
        }
    }
    /*
     Unconfined A: Thread[main,5,main]
     Unconfined B: Thread[main,5,main]
     Default: Thread[DefaultDispatcher-worker-1,5,main]
     New MyThread1: Thread[MyThread1,5,main]
     New MyThread2: Thread[MyThread2,5,main]
     main: Thread[main,5,main]
     */
    println(job) // StandaloneCoroutine{Completed}@4c203ea1
}