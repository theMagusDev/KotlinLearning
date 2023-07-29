package kotlinInDepth.coroutines.controlflow.dispatching

import kotlinx.coroutines.*
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.atomic.AtomicInteger

/*
 While coroutines give you a thread-independent way to implement
suspendable computations, they still need to be associated with some
thread(s) when run. The coroutine library includes a special component
whose task is to control which thread is used to execute a particular
coroutine. This component is called a coroutine dispatcher.

 Dispatcher is a part of coroutine context so you can specify it
in coroutine builder functions like `launch()` and `runBlocking()`.
Since dispatcher is also a singleton context by itself, you
can simply pass it as a context parameter:
 */

fun program1() {
    runBlocking {
        launch(Dispatchers.Default) { // running coroutine using global thread pool dispatcher
            println("Current thread: ${Thread.currentThread().name}")
            // Current thread: DefaultDispatcher-worker-1
        }
    }
}

/*
 Coroutine dispatchers are somewhat similar to Java executors which
distribute threads between a set of parallel tasks. In fact, you can easily
convert the existing implementation of the Executor into a respective
coroutine dispatcher by using the asCoroutineDispatcher() extension
function.
 In the following example, we create a pool-based executor service
with the custom thread factory which assigns names like WorkerThread1,
WorkerThread2, … to executor threads. Then, we convert it into a
dispatcher and use it to start several coroutines in parallel.
 */

fun program2() {
    val id = AtomicInteger(0)
    val executor = ScheduledThreadPoolExecutor(/* corePoolSize = */ 5) { /* thread factory = */ runnble ->
        Thread(
            /* target = */ runnble, //  the object whose run method is invoked when this thread is started.
            /* name = */ "WorkerThread-${id.incrementAndGet()}"
        ).also { it.isDaemon = true }
    }
    executor.asCoroutineDispatcher().use /* block = */ { dispatcher ->
        runBlocking {
            for (i in 1..3) {
                launch(dispatcher) {
                    println("Current thread name: ${Thread.currentThread().name}")
                    delay(1000)
                }
            }
        }
    }
}
/*
 Current thread name: WorkerThread-1
 Current thread name: WorkerThread-2
 Current thread name: WorkerThread-3
 */

/*
 The coroutines library also comes with a set of out-of-the box
dispatcher implementations. Some of them can be accessed
via the Dispatchers object:
 1) Dispatchers.Default: This is a shared tread pool whose size is by
default equal to the number of available CPU cores or 2. This implementation
is generally suited for CPU-bound computations where the task performance
is limited primarily by the CPU speed.
 2) Dispatchers.IO: This is a similar implementation based on a thread
pool which is optimized for running potentially blocking I/O-intensive
tasks such as network requests and reading/writing files. This dispatcher
shares the thread pool with the default implementation adding or
terminating extra threads when necessary.
 3) Dispatchers.Main: This is a dispatcher which operates exclusively in
the UI event thread where the user input is processed.

 It’s also possible to create a dispatcher based on a private thread pool or
even a single thread by using either the newFixedThreadPoolContext(), or
newSingleThreadPoolContext() function. For example, we can rewrite our
Executor-based sample as follows:
 */

@OptIn(DelicateCoroutinesApi::class)
fun program3() {
    newFixedThreadPoolContext(nThreads = 5, name = "WorkerThread").use {
        dispatcher -> runBlocking {
            for (i in 1..3) {
                launch(context = dispatcher) {
                    println("Current thread: ${Thread.currentThread().name}")
                    delay(1000)
                }
            }
        }
    }
}
/*
 Current thread: WorkerThread-1
 Current thread: WorkerThread-2
 Current thread: WorkerThread-3
 */

/*
 When the dispatcher is not specified explicitly (like we did in earlier
examples), it’s automatically inherited from the scope you use to start a
coroutine. Consider the following example:
 */

fun program4() {
    runBlocking {
        println("Root: ${Thread.currentThread().name}")
        launch {
            println("Nested, inherited: ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) {
            println("Nested, explicit: ${Thread.currentThread().name}")
        }
    }
}
/*
 Root: main
 Nested, inherited: main
 Nested, explicit: DefaultDispatcher-worker-2
 */

// Note: runBlocking() builder is confined to the current thread.

/*
 The coroutine does not need to have the same dispatcher throughout its
entire lifetime. Since the dispatcher is a part of coroutine context, it
can be overridden by using the withContext() function:
 */

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
fun program5() {
    newSingleThreadContext(name = "Worker").use { worker ->
        runBlocking {
            println("runBlocking() is starting. Thread: ${Thread.currentThread().name}")
            withContext(context = worker) {
                println("withContext() thread: ${Thread.currentThread().name}")
            }
            println("runBlocking() is ending. Thread: ${Thread.currentThread().name}")
        }
    }
}
/*
 runBlocking() is starting. Thread: main
 withContext() thread: Worker
 runBlocking() is ending. Thread: main
 */

fun main() {
    program1()
    program2()
    program3()
    program4()
    program5()
}
