package practice

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class Person (val name: String?) {

    fun appraiseNameLength(): String {
        if (this.name != null) {
            return when (this.name.length) {
                in 1..5 -> "OK length"
                in 6..10 -> "Pretty long"
                in 11..26 -> "Extremely long"
                else -> "Invalid"
            }
        }
        return "null"
    }
}

fun main() {
    val person1 = Person("Yuriy")
    val person2 = Person("Anastasia")
    val person3 = Person("Abdurahmangaji")
    println(person1.appraiseNameLength())
    println(person2.appraiseNameLength())
    println(person3.appraiseNameLength())

    val block = Block("green")
    Block.BlockProperties.length = 5
    Block.BlockProperties.width = 5
    println(Block.BlockProperties.blocksInBox(11, 11))
}

class Block(val color: String) {
    object BlockProperties {
        var length = 6
        var width = 4

        fun blocksInBox(length: Int, width: Int): Int {
            return (length / this.length) * (width / this.width)
        }
    }
}

