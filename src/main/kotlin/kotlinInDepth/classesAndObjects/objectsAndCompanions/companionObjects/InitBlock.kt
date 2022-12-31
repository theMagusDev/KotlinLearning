package kotlinInDepth.objectsAndCompanions.companionObjects

// Note also that the init blocks in companion objects can be used
// similar to Java static initializers.

class Game {
    companion object {
        init {
            println("Companion object init block (in Java: Game class static init block)")
        }
    }

    object Abc {
        init {
            println("Object init block")
        }
    }

    init {
        println("Game instance's init block")
    }

    constructor() {
        println("Game instance's constructor")
    }
}

fun main() {
    val game = Game()
    // Companion object init block (in Java: Game class static init block)
    // Game instance's init block
    // Game instance's constructor
    Game.Abc
    // Object init block
}