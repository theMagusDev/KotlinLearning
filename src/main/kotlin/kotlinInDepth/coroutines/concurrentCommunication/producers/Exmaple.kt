package kotlinInDepth.coroutines.concurrentCommunication.producers

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
There is a special `producer()` coroutine builder which allows you to
construct concurrent data stream similar to the `sequence()` function we’ve
discussed earlier when talking about the Collection API. This builder
introduces ProducerScope which provides the send() method similar to a channel:
 */

@OptIn(ExperimentalCoroutinesApi::class)
fun main() {
    runBlocking {
        val channel = produce<Int> {
            for (n in 1..3) {
                val square = n * n
                println("Sending: $square")
                send(square)
            }
        }
        launch {
            channel.consumeEach { println("Received: $it") }
        }
    }
}

/*
 Note that you do not need to explicitly close a channel in this case;
the `producer()` builder will do it automatically on coroutine termination.
 In terms of exception handling, produce() follows the policy of
async()/await(): an exception thrown inside produce() is preserved and
rethrown in the first coroutine which invokes the channel’s receive().
 */
