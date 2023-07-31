package kotlinInDepth.coroutines.concurrentCommunication.channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.nio.channels.ClosedChannelException
import kotlin.random.Random

/*
 Channels offer you a convenient way to share an arbitrary data stream
between coroutines. The basic operations on any channel represented by the
`Channel` interface is sending data elements by the `send()` method and
receiving them by the `receive()` method. When these methods can’t complete
their work – for example, when the channel's internal buffer is full and you
try to send data to it – they suspend the current coroutine and resume them
later when it’s possible. That's the major difference between channels and
blocking queues which play a similar role in Java’s concurrency API
but work by blocking calling the thread.
 Channels can be constructed by the generic Channel() function which takes
an integer value describing the channel capacity. One of the basic
implementations is a channel with an internal buffer of a limited size. When
the buffer is full, a call to send() is suspended until at least one element is
received. Similarly, a call to receiver() suspends when the buffer is empty
until at least one element gets sent:
 */

fun program1() {
    runBlocking {
        val streamSize = 5
        val channel = Channel<Int>(capacity = 3)
        launch {
            for (n in 1..streamSize) {
                delay(Random.nextLong(until = 100)) // suspend function call
                val square = n * n
                println("Sending: $square")
                channel.send(square) // suspend function call
            }
        }
        launch {
            for (i in 1..streamSize) {
                delay(Random.nextLong(100)) // suspend function call
                val n = channel.receive() // suspend function call
                println("Received: $n")
            }
        }
    }
}
/* Output (order is not guaranteed):
 Sending: 1
 Received: 1
 Sending: 4
 Received: 4
 Sending: 9
 Sending: 16
 Received: 9
 Sending: 25
 Received: 16
 Received: 25
 */

/*
 Although, the output may vary depending on actual delays and other
circumstances, channels ensure that all values are received in the same
order as they are being sent (16 and 25 send, 16 first received, not 25).

 The Channel() function can also take some special values which produce
channels with different behavior. These values are represented by constants
in the companion object of the Channel interface:
 * Channel.UNLIMITED (= Int.MAX_VALUE): This is a channel with
unlimited capacity whose internal buffer grows on demand. Such
channels never suspend on send(), but can suspend on receiver()
when the buffer is empty.
 * Channel.RENDEZVOUS (= 0): This is a channel which has no internal buffer.
Any call to send() suspends until some other coroutine invokes receive().
Similarly, the receive() call is suspended until someone invokes send().
This channel is created by default when you omit capacity argument.
Unbuffered channels transfer elements when sender and receiver meet each other.
 * Channel.CONFLATED (= -1): This is a conflated channel which stores
at most one element which is overwritten by send() so that any
unread sent values are lost. In this case, the send() method never
suspends.
 Any positive value less than UNLIMITED produces a channel with a limited-size buffer.

 The Rendezvous channel ensures that producer and consumer coroutines
are activated in turns:
 */
fun program2() {
    runBlocking {
        val streamSize = 5
        val channel = Channel<Int>(capacity = Channel.RENDEZVOUS)
        launch {
            for (n in 1..streamSize) {
                delay(Random.nextLong(until = 100)) // suspend function call
                val square = n * n
                println("Sending: $square")
                channel.send(square) // suspend function call
            }
        }
        launch {
            for (i in 1..streamSize) {
                delay(Random.nextLong(100)) // suspend function call
                val n = channel.receive() // suspend function call
                println("Received: $n")
            }
        }
    }
}
/*
 Sending: 1
 Received: 1
 Sending: 4
 Received: 4
 Sending: 9
 Received: 9
 Sending: 16
 Received: 16
 Sending: 25
 Received: 25
 */

/*
 Let’s modify our first example by setting the
consumer delay to be twice as much as producer’s:
 */
fun program3() {
    runBlocking {
        val streamSize = 5
        val channel = Channel<Int>(capacity = Channel.CONFLATED)
        launch {
            for (n in 1..streamSize) {
                delay(100)
                val square = n * n
                println("Sending: $square")
                channel.send(square)
            }
        }
        launch {
            for (i in 1..streamSize) {
                delay(200)
                val n = channel.receive()
                println("Received: $n")
            }
        }
    }
}
/*
 Sending: 1
 Received: 1
 Sending: 4
 Sending: 9
 Received: 9
 Sending: 16
 Sending: 25
 Received: 25
 */

/*
 If you run the preceding program, you’ll also see that it doesn't terminate
after printing the last line. The reason is that our receiver expects to get at
least 5 values since we’re iterating from 1 to streamSize. But since only
about 3-4 values are actually received, this condition can never be satisfied.
What we need in this situation is some kind of signal which
would mean that the channel is closed and won’t send any further data. The
Channel API allows you to do that by calling the close() method on the
producer side. On the consumer side, we can replace the fixed-number loop
with an iteration over the channel data:
 */

fun program4() {
    runBlocking {
        val streamSize = 5
        val channel = Channel<Int>(capacity = Channel.CONFLATED)
        launch {
            for (n in 1..streamSize) {
                delay(100)
                val square = n * n
                println("Sending: $square")
                channel.send(square)
            }
            channel.close()
        }
        launch {
            for (i in 1..streamSize) {
                delay(200)
                try {
                    val n = channel.receive() // ClosedReceiveChannelException can be here
                    println("Received: $n")
                } catch (e: ClosedReceiveChannelException) {
                    println("Channel was closed, so shutting down a receiver...")
                    return@launch
                }
            }
        }
    }
}
/*
 Sending: 1
 Received: 1
 Sending: 4
 Sending: 9
 Received: 9
 Sending: 16
 Sending: 25
 Received: 25
 Channel was closed, so shutting down a receiver...
 */

/*
 After the channel is closed, any attempt to call send() will fail with
ClosedSendChannel Exception. Calls to receive() will return unread
elements until the channel is exhausted after which they will throw
ClosedSendChannelException as well.
 */

// Or replace try-catch with iteration over the channel data.
// On the consumer side, you can also use the consumeEach() function to read
// all channel content instead of explicit iteration:

fun program5() {
    runBlocking {
        val streamSize = 5
        val channel = Channel<Int>(capacity = Channel.CONFLATED)
        launch {
            for (n in 1..streamSize) {
                delay(100)
                val square = n * n
                println("Sending: $square")
                channel.send(square)
            }
            channel.close()
        }
        launch {
            channel.consumeEach { value ->
                delay(200)
                println("Received $value")
            }
            /* Or the same:
            for (n in channel) {
                delay(200)
                println("Received: $n")
            }
             */
        }
    }
}
/*
 Sending: 1
 Sending: 4
 Received: 1
 Sending: 9
 Sending: 16
 Received: 4
 Sending: 25
 Received: 16
 Received: 25
 */

/*
Why output is that even when we use CONFLATED?
 sender sends value (1/5) and waits 100ms
 receiver started its execution and waits 200ms
 sender sends value (2/5) and waits 100ms
 receiver starts getting values (1/2)
 sender sends value (3/5) and waits 100ms
 sender sends value (5/5) and waits 100ms
 receiver gets value (2/2) from channel.
 sender sends value (5/5) and waits 100ms
 receiver starts getting values (4/5)
 receiver gets value (5/5) from channel.
 */

fun main() {
    program1()
    program2()
//  program3() // function never ends
    program4()
    program5()
}

