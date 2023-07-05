package kotlinInDepth.classesAndObjects.understandingClassHierarchies.subclass

// A powerful feature of inheritance is a so called ad-hoc polymorphism which
// allows you to provide different implementations of a superclass member for
// particular subclasses and choose them depending on the actual instance
// class at runtime. In Kotlin, this can be achieved by overriding a member of
// the superclass:

open class Animal(var age: Int) {
    open fun eat() { // open for an ability to be overriden
        println("Animal eating...")
    }
    fun sleep() = "Animal is sleeping."
}

class Tiger(age: Int) : Animal(age) {
    override fun eat() {
        println("Tiger eats...")
    }
}

class Cat(age: Int) : Animal(age) {
    override fun eat() {
        println("Cat eats...")
    }
    // The sleep() method, on the other hand, is final since
    // it's not explicitly marked as open and so can not be overriden:
//  override fun sleep() = "Cat is sleeping" // Error: 'sleep' in 'Animal' is final and cannot be overridden
}

/* Two major differences between overriding in Kotlin and Java:
 1) Kotlin functions and properties are final by default and
must be explicitly marked with the open keyword to permit
overriding in subclasses
 2) overridden members in Kotlin must be always accompanied
by the 'override' keyword. Failing to do so produces a compilation error.
 */

fun program3() {
    val tiger = Tiger(age = 5)
    val cat = Cat(age = 3)

    tiger.eat() // Tiger eats...
    cat.eat() // Cat eats...
}



fun main() {
    program3()
}
