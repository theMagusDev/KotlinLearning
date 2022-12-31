package kotlinInDepth.objectsAndCompanions.objects

class Player(val id: Int) {
    object Properties {
        /* Default player speed in playing field – 7 cells per turn */
        val defaultSpeed = 7

        fun calcMovePenalty(cell: Int): Int {
            return -1
        }

        // But You cannot use the properties and the functions
        // of the outer class in the inner.
        // val superMegaSpeed = superSpeed * 4 // Unresolved reference: superSpeed
    }

    // You can also use properties and functions from
    // a nested object in the outer class.
    val superSpeed = Properties.defaultSpeed * 2 // 14
}

fun main() {
    println(Player.Properties.defaultSpeed) // 7

    // The object Properties has the scope Player,
    // which means we can access it only through Player.Properties.
    // That's how you can create a singleton connected to a special class.
}

// As you can see, it's similar to static in Java.