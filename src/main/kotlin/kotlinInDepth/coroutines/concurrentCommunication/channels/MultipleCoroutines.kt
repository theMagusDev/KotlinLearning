package kotlinInDepth.coroutines.concurrentCommunication.channels

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

/*
 Channel communication does not necessarily involve just a single producer
and a single consumer. For example, the same channel can be concurrently
read by multiple coroutines. This is called fanning out:
 */
fun program6() {
    runBlocking {
        val streamSize = 5
        val channel = Channel<Int>(capacity = 2)
        launch {
            for (n in 1..streamSize) {
                val square = n * n
                println("Sending: $square")
                channel.send(square)
            }
            channel.close()
        }

        for (i in 1..3) {
            launch {
                channel.consumeEach {
                    println("Received by consumer #$i: $it")
                    delay(1000)
                }
            }
        }
    }
}

/*
 Sending: 1
 Sending: 4
 Sending: 9
 Received by consumer #1: 1
 Received by consumer #2: 4
 Received by consumer #3: 9
 Sending: 16
 Sending: 25
 (1000 ms passed)
 Received by consumer #3: 16
 Received by consumer #3: 25
 */

fun main() {
    program6()
}
