package kotlinInDepth.coroutines.introduction.context

import kotlinx.coroutines.*

/*
 Each coroutine has an associated context which is represented by the
CoroutineContext interface and can be accessed by the
coroutineContext property of the enclosing scope. The context is an
immutable collection of key-value pairs which contains various data
available to the coroutine. Some of them have a special meaning for the
coroutine machinery and affect how a coroutine gets executed at runtime.

 The following elements are of particular interest:
 1) `Job` which represents the cancellable task performed by the coroutine.
 2) `Dispatcher` which controls how coroutines are associated with
threads.
 3) `ExceptionHandler` which handles uncaught exceptions in a coroutine
or its children. You can only print an info about occurred exception, but
you cannot recover from it in the CoroutineExceptionHandler.

 The context can store any data implementing `CoroutineContext.Element`.
To access a particular element, you can use the get() method
or indexing operator supplying a corresponding key:
 */
fun program() {
    GlobalScope.launch {
        println("Task is active: ${coroutineContext[Job.Key]?.isActive ?: "Error: can not get Job instance!"}")
        // Job.Key is key for Job instance in the coroutine context.

        Thread.sleep(100) // sleep to see a result output
    }
}

/*
 Task is active: true
 */

/*
 By default, coroutines created by standard builders like launch() or
async() inherit their context from the current scope. When necessary, you
can supply a different one by using the context parameter of the
corresponding builder function. To create a new context, you may use the
`+` operator which merges data from two contexts together. Remember that
last written parameter overrides previous ones.
 */

private fun CoroutineScope.showName() {
    // CoroutineName is data class
    println("Current coroutine: ${coroutineContext[CoroutineName]?.name}")
}

fun main() {
    runBlocking {
        showName() // Current coroutine: null
        launch(context = coroutineContext + CoroutineName("Worker")) {
            showName() // Current coroutine: Worker
        }
    }
}

/*
 You can also switch context during the coroutine execution using the
`withContext()` function which takes a new context and a suspending
lambda. This can be useful, for example, if you want to run some block of
code inside a different thread. We’ll take a look at an example of such
thread-jumping in a section about coroutine dispatchers
 */