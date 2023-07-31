package kotlinInDepth.coroutines.concurrentCommunication.flows

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun program1() {
    runBlocking {
        val flow = flow<Int> {
            for(n in 1..3) {
                val square = n * n
                println("Sending: $square")
                emit(square)
            }
        }
        launch {
            flow.collect(collector = { println("Receiving: $it") })
        }
    }
}
/*
 Sending: 1
 Receiving: 1
 Sending: 4
 Receiving: 4
 Sending: 9
 Receiving: 9
 */

/*
Producers VS Flows
*` produce()` -> `flow()` builder;
* `send()` -> `emit()` to generate individual flow items instead of sending them to a channel;
* channel’s `consumeEach()` -> `collect()` function to consume flow items inside another coroutine.
 */

/*
First, unlike producers, flows are multi-use which means that they are
started anew for each collect() call. This can be compared to behavior of
sequences generated by the sequence() builder:
 */

fun program2() {
    runBlocking {
        val flow = flow<Int> {
            for (n in 1..3) {
                val square = n * n
                println("Sending: $square")
                emit(square)
            }
        }
        launch {
            flow.collect(collector = { println("Receiving #1: $it") })
        }
        launch {
            flow.collect(collector = { println("Receiving #2: $it") })
        }
    }
}
/* Output (with a possible change in order between collectors #1 and #2):
 Sending: 1
 Receiving #1: 1
 Sending: 4
 Receiving #1: 4
 Sending: 9
 Receiving #1: 9
 Sending: 1
 Receiving #2: 1
 Sending: 4
 Receiving #2: 4
 Sending: 9
 Receiving #2: 9
 */

/*
 In the case of our channel/producer example, an asynchronous data generation
is launched by the coroutine builder itself and is shared between all consumers.
So adding a second consumer coroutine would produce something as follows:

-----------------------------------------------
 Sending: 1
 Receiving #1: 1
 Sending: 4
 Sending: 9
 Receiving #1: 4
 Receiving #2: 9
-----------------------------------------------

 In other words, each value would be sent and received exactly once.

 Besides creating flows by a builder function, you can also transform a fixed set
of values using the flowOf() into a flow or transform a collection using asFlow():
 */
val fixedFlow = flowOf("abc", "def", "ghi")
val seqFlow = generateSequence(seed = 1, nextFunction = { 2 * it })
    .take(10)
    .asFlow()

fun main() {
    program1()
    program2()
}
