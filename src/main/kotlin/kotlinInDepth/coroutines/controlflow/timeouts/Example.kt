package kotlinInDepth.coroutines.controlflow.timeouts

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import java.io.File
import java.lang.Exception

/*
In some cases, we can’t wait for completion of a task indefinitely and need
to set up some timeout. The coroutines library has a special withTimeout()
function exactly for this purpose:
 */

fun main() {
    runBlocking {
        // remember: async is used to compute some result and returns Deferred object, on which you can call `await()`
        val asyncData = async { File("D:\\Kotlin\\KotlinLearning\\src\\main\\kotlin\\kotlinInDepth\\coroutines\\controlflow\\timeouts\\data.txt").readText() }

        try {
            val text = withTimeout(timeMillis = 50) { asyncData.await() }
            println("Data loaded: $text")
        } catch (e: TimeoutCancellationException) {
            println("Timeout exceeded")
        }
    }
}
