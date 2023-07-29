package kotlinInDepth.coroutines.introduction.scopes

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// file #1

/*
 So far, our example coroutines were running in the global scope which
means that their lifetime is limited only by that of the entire
application.
 In some cases, though, we may want to ensure that the coroutine
execution is restricted to a particular operation. This is possible
thanks to the parent-child relationship between concurrent tasks: when you
start one coroutine in the context of another, the latter becomes a child
of the former. The lifecycles of parent and child are related so that the
parent coroutine may complete only after completion of all its children.
 This feature is called a structured concurrency. It can be compared to using
blocks and subroutines to constrain a scope of local variables:
 */

fun main() {
    println("Starting a coroutine...")
    runBlocking { // this: CoroutineScope
        println("Parent task started")
        this.launch {
            println("Child task A started")
            delay(200)
            println("Child task A finished")
        }
        launch { // you can omit `this`
            println("Child task B started")
            delay(200)
            println("Child task B finished")
        }
        delay(100)
        println("Parent task finished")
    }
    println("Coroutine finished. Shutting down program...")
}

/*
 Starting a coroutine...
 Parent task started
 Child task A started
 Child task B started
 Parent task finished
 Child task A finished
 Child task B finished
 Coroutine finished. Shutting down program...
 */

/*
 You can see that the main body of the parent coroutine represented by
suspend lambda of the runBlocking() call finishes before its children due
to having a smaller delay of 100 ms. The coroutine itself is not completed at
this point and just waits in a suspended state until the completion of both
the children. After that, the parent coroutine completes as well and since
we’re using the runBlocking() builder here, it also unblocks the main
thread allowing it to print the final message.
 */
