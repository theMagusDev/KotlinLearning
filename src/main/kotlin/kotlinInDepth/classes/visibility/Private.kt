package kotlinInDepth.classes.visibility

/* Private - only in containing class */
class Person public constructor(private val name: String, private val surname: String) {
    // use the constructor keyword if you want to change its visibility
    fun fullName() = "$name + $surname"
}

class Empty private constructor()

fun main() {
    val person = Person("Yuriy", "Magus")
    // println(person.name) // Error: name is not accessible
    println(person.fullName()) // OK

    // val empty = Empty() // Error: can’t invoke private constructor
}