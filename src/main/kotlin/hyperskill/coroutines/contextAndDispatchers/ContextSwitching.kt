package hyperskill.coroutines.contextAndDispatchers

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

// File #4

/*
Consider a UI application that needs to load some data from disk
to be displayed, which can take some time and therefore can not
be executed in the main thread without freezing the UI. We don't
really want to launch an async job, wait for its execution, and then
render the result. How can we do it in a simpler way? Kotlin offers
a solution for that problem, too. It's called `withContext()`. As
the name implies, it executes a block of code with a new context.
As shown before, it inherits the context and, if the dispatcher
is specified, switches the execution to a new thread. But now
the execution order is sequential:
 */

fun main() {
    val job = runBlocking {
        // starts in main thread
        println("root: thread=${Thread.currentThread()}, context=${this.coroutineContext}")
        withContext(context = CoroutineName("MyCoroutine"), block = { // continues in the same thread, but overrides coroutine name in context
            println("MyCoroutine: thread=${Thread.currentThread()}, context=${this.coroutineContext}")

            withContext(Dispatchers.IO) {
                println("MyCoroutine->IO: thread=${Thread.currentThread()}, context=${this.coroutineContext}")
            }
        })

        // returns back to the main thread, after IO operation is done
        println("root again: thread=${Thread.currentThread()}, context=${this.coroutineContext}")
    }
}

/*
 root: thread=Thread[main,5,main], context=[BlockingCoroutine{Active}@4f47d241, BlockingEventLoop@4c3e4790]
 MyCoroutine: thread=Thread[main,5,main], context=[CoroutineName(MyCoroutine), kotlinx.coroutines.UndispatchedMarker@4ca8195f, UndispatchedCoroutine{Active}@65e579dc, BlockingEventLoop@4c3e4790]
 MyCoroutine->IO: thread=Thread[DefaultDispatcher-worker-1,5,main], context=[CoroutineName(MyCoroutine), kotlinx.coroutines.UndispatchedMarker@4ca8195f, DispatchedCoroutine{Active}@4d26599e, Dispatchers.IO]
 root again: thread=Thread[main,5,main], context=[BlockingCoroutine{Active}@4f47d241, BlockingEventLoop@4c3e4790]
 */

// Unlike in the previous example, the execution order is guaranteed in this case.