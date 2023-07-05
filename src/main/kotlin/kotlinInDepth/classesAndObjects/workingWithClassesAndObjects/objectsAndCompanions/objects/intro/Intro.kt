package kotlinInDepth.objectsAndCompanions.objects.intro

// Singleton is a design pattern that ensures that a class has
// only one instance with global access to it. This means we can get
// an instance of a singleton class anywhere in the code.

// Consider a simple analogy: when playing a board game, all players
// act within the field of the game. This is a specific single field
// where the global state of the game is stored.

/* Main singleton features:
* Singleton class has only a single instance.
* Singleton class provides a global access point.
 */

class Player

object PlayingField {
    val players: Array<Player> = emptyArray()

    fun getAllPlayers(): Array<Player> {
        return players
    }

    fun isPlayerInGame(player: Player): Boolean {
        return player in players
    }

}