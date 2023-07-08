package kotlinInDepth.classesAndObjects.understandingClassHierarchies.secondPart.interfaces

// File #1

// An interface is a type which can contain methods and properties
// (both abstract and non-abstract), but can’t define neither instance state nor
// constructors.

interface Vehicle {
    var currentSpeed: Int
    fun move()
    fun stop()
}

// Interface members are abstract by default

// Interfaces can be supertypes for both classes and other interfaces. When a
// non-abstract class inherits an interface, it must provide implementations for
// all abstract members (and may optionally override non-abstract ones).
// Similar to class-to-class inheritance, implementations of interface members
// must be marked with the override keyword.

interface FlyingVehicle : Vehicle {
    var currentHeight: Int
    fun takeOff()
    fun land()
}
class Truck : Vehicle {
    override var currentSpeed = 0
    override fun move() {
        println("Riding...")
        currentSpeed = 50
    }
    override fun stop() {
        println("Stopped")
        currentSpeed = 0
    }
}
class Aircraft : FlyingVehicle {
    override var currentSpeed = 0
    override var currentHeight = 0

    override fun move() {
        println("Taxiing...")
        currentSpeed = 50
    }

    override fun stop() {
        println("Sopped")
        currentSpeed = 0
    }

    override fun takeOff() {
        println("Taking off...")
        currentSpeed = 500
        currentHeight = 5000
    }

    override fun land() {
        println("Landed")
        currentSpeed = 50
        currentHeight = 0
    }
}

// Note an absence of () after the supertype name in the definitions of all
// three types. This is explained by the fact that, unlike classes, interfaces
// have no constructors and thus no code to call upon the subclass initialization.

// Similarly to Java, Kotlin interfaces are not allowed to inherit from classes.
// The Any class can be considered an exception of sort since it’s implicitly
// inherited by each Kotlin class and interface.


