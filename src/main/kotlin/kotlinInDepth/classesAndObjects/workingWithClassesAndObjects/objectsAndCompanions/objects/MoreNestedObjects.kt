package kotlinInDepth.objectsAndCompanions.objects

class Player1 private constructor(val id: Int) {
    object Properties {
        /* Default player speed in playing field – 7 cells per turn */
        val defaultSpeed = 7

        fun calcMovePenalty(cell: Int): Int {
            return -1
        }
    }

    /* creates a new instance of Player */
    object Factory {
        fun create(playerId: Int): Player1 {
            return Player1(playerId)
        }
    }
}

fun main() {
    println(Player1.Properties.defaultSpeed) // 7
    val player1 = Player1.Factory.create(15)
    println(player1.id) // 15

    // constructor is private, so we can create a new Player1
    // instance only through the object Factory.
    // val player2 = Player1(11) // Error
}

// Another useful feature of nested objects is that
// you can declare any number of objects inside another object.

object TheGame {
    object Properties {
        val maxPlayersCount = 13
        val maxGameDurationInSec = 2400
    }

    object Info {
        val name = "My super game"
    }

    // This is helpful for organizing the data in the singletons.
}