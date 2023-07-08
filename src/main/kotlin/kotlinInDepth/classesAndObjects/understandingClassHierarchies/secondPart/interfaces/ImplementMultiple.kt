package kotlinInDepth.classesAndObjects.understandingClassHierarchies.secondPart.interfaces

// Like in Java, classes and interfaces can implement multiple interfaces:

interface Swimable {
    fun swim()
}
interface Helpable {
    fun help()
}
interface RescueAble : Swimable, Helpable
class RescueWorker : Swimable, Helpable {
    override fun swim() {
        println("Swimming")
    }

    override fun help() {
        println("Is helping")
    }
}

// An interesting issue arises when a single type inherits from more than one
// different interface which has members with the same signatures.

// Case #1: same function's names, but only one interface gives a default implementation
interface Floatable {
    fun move() {
        println("I'm floating")
    }
}
interface Diveable {
    fun move()
}
class Submarine : Floatable, Diveable {
    override fun move() {
        super.move() // Use Floatable move()
    }
}

// Case #2: same function's names, both interfaces give a default implementation
interface Car {
    fun move() {
        println("I'm riding")
    }
}
interface Ship {
    fun move() {
        println("I'm sailing")
    }
}
class Amphibia : Car, Ship {
    override fun move() {
//      super.move() // Error: Many supertypes available, please specify the one you mean in angle brackets, e.g. 'super<Foo>'
        super<Car>.move() // OK, using Car's move()
        super<Ship>.move() // OK, using Ship's move()
    }
}

fun main() {
    val amphibia = Amphibia()
    amphibia.move()
    /*
     I'm riding
     I'm sailing
    */
}
