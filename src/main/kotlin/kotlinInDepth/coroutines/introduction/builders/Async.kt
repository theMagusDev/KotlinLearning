package kotlinInDepth.coroutines.introduction.builders

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

// File #2

/*
 If we do need a result, however, there is another builder function which is
called async(). This function returns an instance of Deferred (it's like
Java's Future), a special Job subtype which provides access to the
computation result through the await() method. When invoked, await()
suspends until the computation is either completed (thus, producing a
result), or cancelled. In the latter case, await() fails with an exception.

 public fun <T> CoroutineScope.async(
     context: CoroutineContext,
     start: CoroutineStart,
     block: suspend CoroutineScope.() -> T
 ): Deferred<T>

deferred.await() blocks current thread until receiving a result.
 */

suspend fun main() {
    val message = GlobalScope.async {
        delay(100)
        "Resulting message"
    }
    val count = GlobalScope.async {
        delay(100)
        1 + 2
    }
    delay(200)
    val result = message.await().repeat(count.await())
    println(result) // Resulting messageResulting messageResulting message
}

// By default, both launch() and async() builders run coroutines in a shared
// pool of background threads while the calling thread itself is left unblocked.