package kotlinInDepth.classesAndObjects.understandingClassHierarchies.firstPart.subclass

// To inherit from a given class, you need to add its name preceded by
// the ':' symbol after the primary constructor in your class definition

open class Vehicle {
    var currentSpeed = 0
    fun start() {
        println("I'm moving")
    }
    fun stop() {
        println("Stopped")
    }
}

open class FlyingVehicle : Vehicle() {
    fun takeOff() {
        println("Taking off")
    }
    fun land() {
        println("Landed")
    }
}

class Aircraft(val seats: Int) : FlyingVehicle()

/* Java vs Kotlin
 1) In Kotlin there are no special keywords like extends and
implements in Java. Instead inheritance is always denoted by colon symbol (:).
 2) In Kotlin all classes and functions are final by default.
If you want to inherit/override them, you should mark them as 'open'.
 3) Note the parentheses added after Vehicle and FlyingVehicle in the
definitions of their subclasses. This is in fact a call to the superclass
constructor where you put necessary arguments to the super class
initialization code.
 */

fun program1() {
    // Instances of subclasses are also instances of
    // their superclasses and also inherit super class members
    val aircraft = Aircraft(seats = 100)
    val vehicle: Vehicle = aircraft

    vehicle.start()
    vehicle.stop()
//  vehicle.takeOff() // Unresolved reference: takeOff

    aircraft.start()
    aircraft.takeOff()
    println(aircraft.seats)
}

/* Some classes do not support inheritance to the full extent. In particular:

 1) Data classes are always final and can’t be declared as open:
open data class Person(val name: String, val age: Int) // Error: Modifier 'open' is incompatible with 'data'

 2) Value (Inline) classes can not inherit and be inherited by other classes:
@JvmInline
open value class Dollar(val amount: Int) // Error: Value classes can be only final
value class Dollar(val amount: Int) : Any() // Error: Value class cannot extend classes

 */

// Objects (including companions) can be freely inherit open classes
open class Person(val name: String, var age: Int) {
    companion object : Person("Unknown", 0)
}
object John : Person("John", 32)
fun program2() {
    println(Person.name) // Unknown
    println(Person.age) // 0
}

fun main() {
    program1()
    program2()
}


