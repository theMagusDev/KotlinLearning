package kotlinInDepth.classes.constructor
// File #4

/*
Primary constructor parameters may not be used outside property
initializers and init blocks.
 */
class Person4(name: String, surname: String) {
    val fullName = "$name + $surname"

    fun printFirstName() {
        // println(name) // Error: 'name' is not available
    }
}

// A possible solution
class Person5(name: String, surname: String) {
    val fullName = "$name + $surname"
    val name = name

    fun printFirstName() {
        println(name) // OK
    }
}

// Kotlin, however, provides out-of-the-box solution
class Person6(val name: String, surname: String) {
    val fullName = "$name + $surname"

    fun printFirstName() {
        println(name) // OK
    }
}