package kotlinInDepth.classes.constructor
// File #2

class Person2 (name: String, surname: String) {
    val fullName = "$name $surname"
    init {
        println("Created new Person instance: $fullName")
    }
    init {
        println("Second init{} block executed")
    }
}

fun main() {
    val person2 = Person2("Ivan", "Hobs")
    // Output:
    // Created new Person instance: Ivan Hobs
    // Second init{} block executed
}