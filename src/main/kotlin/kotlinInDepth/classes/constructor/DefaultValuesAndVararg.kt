package kotlinInDepth.classes.constructor
// File #5

class Person7(val name: String, val surname: String = "") {
    fun fullName() = "$name $surname"
}

class Room(vararg val persons: Person7) {
    fun showNames() {
        for (person in persons)
            println(person.fullName())
    }
}
fun main() {
    val room = Room(
        Person7("John"),
        Person7("Jane", "Smith"),
        Person7("Ivan", "Hobs")
    )
    room.showNames()
    /*
    John
    Jane Smith
    Ivan Hobs
     */
}