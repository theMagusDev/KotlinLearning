package kotlinInDepth.objectsAndCompanions.objects.intro

// When you use object declaration, the constructor is not
// available because Kotlin does it itself. To get an instance
// of our playing field, use PlayingField declaration.
// We can use it anywhere, and it will return the same object every time.

fun startNewGameTurn() {
    val players = PlayingField.getAllPlayers()
    if (players.size < 2) {
        return println("The game cannot be continued without players")
    }
    for (player in players) {
        nextPlayerTurn(player)
    }
}

fun nextPlayerTurn(player: Player) {
    if (!PlayingField.isPlayerInGame(player)) {
        return println("Current player lost. Next...")
    }
    /* Player actions here */
}