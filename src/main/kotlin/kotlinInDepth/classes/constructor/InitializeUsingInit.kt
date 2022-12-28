package kotlinInDepth.classes.constructor
// File #3

class Person3(fullName: String) {
    val name: String // not initialized, so must be initialized later.
    val surname: String // same here
    init {
        val names: List<String> = fullName.split(" ")
        if (names.size != 2)
            throw IllegalArgumentException("Invalid name: $fullName")
        name = names[0]
        surname = names[1]
    }
}

fun main() {
    val person3 = Person3("Ivan Hobs")
    println(person3.name) // Ivan
    println(person3.surname) // Hobs
}
