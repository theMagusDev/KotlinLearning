package kotlinInDepth.classesAndObjects.understandingClassHierarchies.subclassInitialization

// This is another, more explicit example of "leaking this" problem:

open class Animal(val name: String, var age: Int) {
    override fun toString() = "$name $age"
    init {
        println(this) // potential danger: Leaking 'this' in constructor of non-final class Animal
    }
}

class Dog(name: String, age: Int, var owner: String) : Animal(name, age) {
    override fun toString() = "$name $age, its owner: $owner"
}

fun main() {
    val dog = Dog(name = "Sharik", age = 3, owner = "John")
    // Sharik 3, its owner: null
}