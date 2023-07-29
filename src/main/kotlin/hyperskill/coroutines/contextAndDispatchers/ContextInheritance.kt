package hyperskill.coroutines.contextAndDispatchers

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// File #3

/*
An important thing to know about the context is that everything except
for the job is inherited by default unless explicitly overridden. That's
very handy because we usually don't want to specify the same dispatcher
over and over again for all children.
 */
val handler = CoroutineExceptionHandler {coroutineContext, throwable ->
    println("Caught ${throwable.message} in context $coroutineContext")
}

fun main() {
    val job = runBlocking(context = handler) {
        println("root: ${this.coroutineContext}")
        launch { // uses context of the parent, creates a new job
            println("first: ${this.coroutineContext}")
            launch { // still same context, another new job
                println("first->same: ${this.coroutineContext}")
            }
            launch(Dispatchers.Default) { // overrides the dispatcher
                println("first-> default: ${this.coroutineContext}")

                launch(Dispatchers.IO) {  // overrides the dispatcher once more
                    println("first->default->IO: ${this.coroutineContext}")
                }
            }
        }
    }
    /*
     root: [hyperskill.coroutines.contextAndDispatchers.ContextInheritanceKt$special$$inlined$CoroutineExceptionHandler$1@38cccef, BlockingCoroutine{Active}@2db0f6b2, BlockingEventLoop@3cd1f1c8]
     first: [hyperskill.coroutines.contextAndDispatchers.ContextInheritanceKt$special$$inlined$CoroutineExceptionHandler$1@38cccef, StandaloneCoroutine{Active}@490d6c15, BlockingEventLoop@3cd1f1c8]
     first->same: [hyperskill.coroutines.contextAndDispatchers.ContextInheritanceKt$special$$inlined$CoroutineExceptionHandler$1@38cccef, StandaloneCoroutine{Active}@1c53fd30, BlockingEventLoop@3cd1f1c8]
     first-> default: [hyperskill.coroutines.contextAndDispatchers.ContextInheritanceKt$special$$inlined$CoroutineExceptionHandler$1@38cccef, StandaloneCoroutine{Active}@7f2fa4d9, Dispatchers.Default]
     first->default->IO: [hyperskill.coroutines.contextAndDispatchers.ContextInheritanceKt$special$$inlined$CoroutineExceptionHandler$1@38cccef, StandaloneCoroutine{Active}@1252c693, Dispatchers.IO]
     */
}

// Since we use launch(), the execution order is not guaranteed in this case.