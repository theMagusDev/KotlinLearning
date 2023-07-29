package hyperskill.coroutines.stoppingScopedJobs

import kotlinx.coroutines.*

suspend fun loadImage(name: String) {
    delay(50)
    println("Loaded $name")
}

suspend fun preCache(name: String) {
    delay(100)
    println("Cached $name")
}

suspend fun loadScreenInGlobalScope() {
    GlobalScope.launch { loadImage("img_1.jpg") }
    GlobalScope.launch { loadImage("img_2.jpg") }
    GlobalScope.launch { preCache("img_3.jpg") }
    throw Exception("Unexpected failure") // simulate crash in main code
}

suspend fun main() {
    runCatching {
        loadScreenInGlobalScope()
    }
    delay(200) // wait long enough to see the results
}
