package kotlinInDepth.coroutines.introduction.scopes

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// file #2

/*
You can also introduce a custom scope by wrapping a code block inside the
coroutineScope() call. Similarly to runBlocking(), this function returns
the value of its lambda and doesn't complete until all its children reach
completion. The main different between coroutineScope() and runBlocking()
is that the coroutineScope() is a suspending function which doesn't
block the current thread:
 */

fun main() {
    runBlocking {
        println("Custom scope start")
        coroutineScope {
            launch {
                println("Task A started")
                delay(100)
                println("Task A finished")
            }
            launch {
                println("Task B started")
                delay(100)
                println("Task B finished")
            }
        }
        println("Custom scope finished")
    }
}

/*
 Custom scope start
 Task A started
 Task B started
 Task A finished
 Task B finished
 Custom scope finished
 */
