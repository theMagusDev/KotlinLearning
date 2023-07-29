package hyperskill.coroutines.stoppingScopedJobs

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Let's create our own scope now with a handy builder function coroutineScope:

suspend fun loadScreenInOwnScope(): Unit = coroutineScope { // this: CoroutineScope, overrides Job
    launch { loadImage("img_1.jpg") }
    launch { loadImage("img_2.jpg") }
    launch { preCache("img_3.jpg") }
    throw Exception("Unexpected failure") // simulate crash in main code
}

suspend fun main() {
    runCatching { // catch all exceptions
        loadScreenInOwnScope()
    }
    delay(200)
}

/*
Now, it will print nothing, even with a 200ms delay at the end,
because the exception inside the scope will cancel
all unfinished operations.
 */