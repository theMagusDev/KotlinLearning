package kotlinInDepth.coroutines.introduction.suspendingFunctions

import kotlinx.coroutines.delay

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

