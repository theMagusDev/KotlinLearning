package hyperskill.coroutines.cancelJobs

import hyperskill.coroutines.stoppingScopedJobs.loadImage
import hyperskill.coroutines.stoppingScopedJobs.preCache
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// With an access to the context, we can now have even more control.
// It offers two methods to cancel work: cancel() and cancelChildren()

suspend fun loadScreenInOwnScope(): Unit = coroutineScope {
    launch { loadImage("img_1.jpg") }
    launch { loadImage("img_2.jpg") }
    launch { preCache("img_3.jpg") }
    this.coroutineContext.cancel()
}

suspend fun main() {
    runCatching { // catch all exceptions
        loadScreenInOwnScope()
    }
    delay(200)
}

// we'll get the same result — nothing will get printed