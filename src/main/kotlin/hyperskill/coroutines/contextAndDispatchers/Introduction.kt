package hyperskill.coroutines.contextAndDispatchers

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

// File #1

fun program1() {

    val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Exception ${throwable.message} was caught in $coroutineContext")
    }

    val supervisedScope = CoroutineScope(SupervisorJob() + handler)

    println(supervisedScope)
    // CoroutineScope(coroutineContext=[
    // SupervisorJobImpl{Active}@23ab930d,
    // hyperskill.coroutines.contextAndDispatchers.ExampleKt$main$$inlined$CoroutineExceptionHandler$1@4534b60d
    // ])
}

/*
The most important elements we can add to
the context are Job, ExceptionHandler, and
Dispatcher (the list is not exhaustive).

 Job represents the work itself, including the hierarchy
of children jobs. It has a state (Active/Completed/Cancelled) and
defines how a failure or cancellation propagates
to parent and children jobs.
 ExceptionHandler handles uncaught exceptions in a coroutine
or its children. It can be very helpful for logging, but note that
it doesn't provide a way to catch the exception, so it cannot be
used to recover from failures – we have try/catch for that. Also,
it has to be installed on the root coroutine only, because children
automatically propagate uncaught errors to the parent all
the way up to the root.
 Dispatcher defines which thread or threads will
be used to run the coroutine code.

 It's important to note that even though we can add multiple jobs,
handlers, or dispatchers to the coroutine builder, a coroutine can
only have one element of each type, so the last one always
overrides all previous. For example,

// CoroutineScope(SupervisorJob() + handler1 + handler2)

will use handler2.

 Another important fact is that if we don't provide an element of
each type, the default one will be used. There is a default
error handler and a default dispatcher for each platform; as
for the job, if it's not provided, a new one will be created for us.
 */

