package kotlinInDepth.coroutines.concurrentCommunication.flows.hyperskill

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

/*
To work with multiple items of the same type, Kotlin offers various
collections: they store the complete set of elements that are all
kept in the memory at the same time. To generate items dynamically
one-by-one, we can use a sequence – it can save memory when we only
need one item at a time. However, the sequence generator function
cannot be suspending, so if getting an item takes a lot of time,
it may become a problem. Getting a potentially unlimited series
of items in a suspending way is a common task: for example,
fetching new messages from a chat API or scanning all folders on
a disk and calculating the size of each one. For these tasks,
we can use flows:
 */

val folderNames = listOf<String>(
    "annotations",
    "basicStatements",
    "classes and Objects",
    "collections",
    "coroutines",
    "functionalProgramming",
    "generics",
    "languageFundamentals",
    "reflection",
    "specialCaseClasses"
)

fun program1() {

    fun calculateSize(): Int {
        Thread.sleep(250)
        val size = Random.nextInt(from = 10, until = 250)
        println("Size calculated: $size mb")
        return size
    }

    fun doSomeWorkWith(folderSize: Int) {
        Thread.sleep(1000)
        println("Item with $folderSize mb was processed")
    }

    val folderSizes = sequence<Int> { // sequence builder lambda begins
        for (folder in folderNames) {
            val size = calculateSize() // long running non-suspending function
            yield(size)
        }
    }

    folderSizes.forEach { doSomeWorkWith(it) }
}

// To allow this code to run a suspending function instead, we
// can use a very similar builder – flow:

fun program2() {

    runBlocking {

        suspend fun calculateSize(): Int {
            delay(1500)
            val size = Random.nextInt(from = 10, until = 250)
            println("Size calculated: $size")
            return size
        }

        suspend fun doSomeWorkWith(folderSize: Int) {
            delay(1000)
            println("Item with $folderSize mb was processed")
        }

        val folderSizes = flow<Int> { // flow builder lambda begins
            for (folder in folderNames) {
                val size = calculateSize() // running suspending function
                emit(size)
            }
        }

        folderSizes.collect { doSomeWorkWith(it) }
    }
}

fun main() {
    println("--- Without flows ---")
    //program1()
    println("--- With flows ---")
    program2()
}
