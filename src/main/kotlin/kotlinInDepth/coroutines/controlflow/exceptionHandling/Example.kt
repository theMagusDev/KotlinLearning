@file:Suppress("UNREACHABLE_CODE")
package kotlinInDepth.coroutines.controlflow.exceptionHandling

import kotlinInDepth.nullability.nullableTypes.exclaim
import kotlinx.coroutines.*

/*
 When it comes to exception handling, various coroutine builders follow one
of the two basic strategies. The first one implemented by builders like
launch() is to propagate exception to the parent coroutine. In this case, the
execution proceeds as follows:
 1. The parent coroutine is cancelled with the same exception as a cause.
This causes it to cancel all the remaining children.
 2. When the children are cancelled, the parent passes an exception to
further up the coroutine tree.

 The process continues until it reaches a coroutine with a global scope.
After that, it’s handled by CoroutineExceptionHandler:
 */

fun program1() {
    runBlocking {
        launch {
            throw Exception("Error in task A")
            println("Task A completed")
        }
        launch {
            delay(1000)
            println("Task B completed")
        }
        println("Root completed")
    }
}

/*
 The top-level coroutine starts two nested tasks with the first one
throwing an exception. This causes cancellation of the root task and both of
its children and since no custom handler is provided, the program falls back
to the default behavior represented by Thread.uncaughtExceptionHandler:

--------------------------- Output --------------------------
 Root completed
 Exception in thread "main" java.lang.Exception: Error in task A
 ...
-------------------------------------------------------------

 CoroutineExceptionHandler defines a single method which takes the
current coroutine context and a thrown exception:

 fun handleException(context: CoroutineContext, exception: Throwable)

 The simplest way to construct a handler is to use the
CoroutineExceptionHandler() function which takes a two-argument lambda:
 */

val handler = CoroutineExceptionHandler {coroutineContext, throwable ->
    println("Caught $throwable")
}

/*
To configure its instance for processing exceptions, you can put it into
coroutine context. Since the handler is a trivial context by itself, you
can just pass it as a context argument into the coroutine builder:
 */

@OptIn(DelicateCoroutinesApi::class)
suspend fun program2() {
    val job = GlobalScope.launch(context = handler) {
        launch {
            throw Exception("Error in Task A")
            println("Task A completed")
        }
        launch {
            delay(1000)
            println("Task B completed")
        }
        println("Root completed")
    }
    job.join()
}
/*
Root completed
Caught java.lang.Exception: Error in Task A
 */

/*
 When no handler instance is defined in context, the coroutines library will
invoke all global handlers configured via the JVM `ServiceLoader`
mechanism as well as `uncaughtExceptionHandler` for the current thread.

Note that CoroutineExceptionHandler can only be specified for a
coroutine launched in the global scope. That’s why we had to replace
runBlocking() with GlobalScope.launch() and mark the main() function
with suspend to make use of suspending join() call. If we’ve retained
runBlocking() from our original example, but supplied it with a handler:
 */

@OptIn(DelicateCoroutinesApi::class)
fun program3() {
    runBlocking(handler) {
        val job = GlobalScope.launch {
            launch {
                throw Exception("Error in Task A")
                println("Task A completed")
            }
            launch {
                delay(1000)
                println("Task B completed")
            }
            println("Root completed")
        }
        job.join()
    }
}
/*
 Root completed
 Exception in thread "DefaultDispatcher-worker-1" java.lang.Exception: Error in Task A
 */

/*
 The program would still use the default exception handler since
our coroutines wouldn't be run in the global scope.

 Another way to handle exception, used by the async() builder, is to
preserve the thrown exception and rethrow it later when the corresponding
await() is called:
 */

fun program4() {
    runBlocking {
        val deferredA = async {
            throw Exception("Error in task A")
            println("Task A completed")
        }
        val deferredB = async {
            println("Task B completed")
        }
        deferredA.await()
        deferredB.await()
        println("Root completed")
    }
}

/*
--------------------------- Output --------------------------
 Exception in thread "main" java.lang.Exception: Error in task A
-------------------------------------------------------------

 The reason is that the exception is re-thrown by deferredA.await() so the
program fails to reach the println(“Root”) statement.
 Note async-like builders which rethrow an exception when you access
coroutine data do not rely on CoroutineExceptionHandler. So even if you
have its instance preconfigured in the coroutine context, it has no effect
(just as we’ve seen in the runBlocking() example); the program will still
fall back to the default handler.

 What if we want to process exceptions thrown by the nested coroutines at
the level of their parent without relying on global handlers? Let's see what
happens if we attempt to process an rethrown exception using the try-catch block:
 */
fun program5() {
    runBlocking {
        val deferredA = async {
            throw Exception("Error in task A")
            println("Task A completed")
        }
        val deferredB = async {
            println("Task B completed")
        }
        try {
            deferredA.await()
            deferredB.await()
        } catch (e: Exception) {
            println("Caught: $e")
        }
        println("Root completed")
    }
}
/*
 Caught: java.lang.Exception: Error in task A
 Root completed
 Exception in thread "main" java.lang.Exception: Error in task A
 */

/*
 The reason is that the exception is rethrown automatically to cancel the
parent coroutine when its child (task A in this case) fails. To override this
behavior, we can use a so called supervisor job.

 With supervisor jobs, the cancellation propagates only in the downward
direction; if you cancel a supervisor, it automatically cancels all its children,
but if a child is cancelled instead, the supervisor and its remaining children
remain active.
 To turn the parent coroutine into a supervisor, we define a new scope using
the supervisorScope() function instead of coroutineScope():
 */

fun program6() {
    runBlocking {
        supervisorScope {
            val deferredA = async {
                throw Exception("Exception in task A")
                println("Task A completed")
            }
            val deferredB = async {
                println("Task B completed")
            }
            try {
                deferredA.await()
            } catch (e: Exception) {
                println("Caught $e")
            }
            deferredB.await()
            println("Root completed")
        }
    }
}
/*
 Task B completed
 Caught java.lang.Exception: Exception in task A
 Root completed
 */

/*
 Note that the supervisor behavior extends to normal cancellations as well:
calling cancel() on one of its children jobs doesn't cause cancellation
of its siblings or supervisor itself. 
 */

fun main() {
//  program1() // Exception here
    runBlocking {
        program2()
    }
//  program3() // Exception here
//  program4() // Exception here
//  program5() // Exception here
    program6()
}

