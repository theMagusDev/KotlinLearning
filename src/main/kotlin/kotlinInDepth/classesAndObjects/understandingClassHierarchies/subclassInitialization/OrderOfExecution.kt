package kotlinInDepth.classesAndObjects.understandingClassHierarchies.subclassInitialization

open class A {
    constructor() {
        println("Executing constructor of A class")
    }

    companion object {
        init {
            println("Initializing class A")
        }
    }

    init {
        println("Initializing object of class A")
    }
}

class B : A {
    constructor() {
        println("Executing constructor of B class")
    }

    companion object {
        init {
            println("Initializing class B")
        }
    }

    init {
        println("Initializing object of class B")
    }
}

fun main() {
    val b = B()
    /* Order of execution:
     Initializing class A
     Initializing class B
     Initializing object of class A
     Executing constructor of A class
     Initializing object of class B
     Executing constructor of B class
     */

    // When you want to create instance of some class, Kotlin sees
    // its constructor and goes to the constructor of its superclass until
    // reaching the Any class constructor. Then Kotlin execute constructors
    // starting from the root class (that is Any) and finishing by required class.

    // It goes so: B -> A -> Any
    // Then it executes so: Any() -> A() -> B()
}