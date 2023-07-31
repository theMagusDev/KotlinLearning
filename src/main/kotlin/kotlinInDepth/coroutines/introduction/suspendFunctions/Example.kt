package kotlinInDepth.coroutines.introduction.suspendFunctions

import kotlinx.coroutines.delay

// Suspend function is a function that could be started, paused, and resume.

suspend fun foo() {
    println("Task started")
    delay(100)
    println("Task finished")
}

/*
 delay() VS Thread.sleep(): instead of blocking the current thread, `delay()`
function suspends the calling function leaving the thread free for execution of other tasks.

 Suspending functions may call both suspending and ordinary functions.
When calling another suspending function such a call becomes a suspension point
where the caller function's execution may be temporarily stopped and resumed later,
while calling an ordinary function proceeds just like in a normal function call.

Kotlin, however, forbids you to call
suspending functions from an ordinary one.
 */

