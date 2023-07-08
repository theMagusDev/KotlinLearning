package kotlinInDepth.classesAndObjects.understandingClassHierarchies.secondPart.abstractClassesAndMembers
import kotlin.math.PI

// Similar to Java, Kotlin supports abstract classes which can’t be instantiated
// directly but instead serve only as super types for other classes.

abstract class Entity(val name: String)
class Person(name: String, val age: Int) : Entity(name) // Ok: delegation call in subclass
// val entity = Entity("Unknown") // Error: Cannot create an instance of an abstract class

// Abstract classes, as you can see in the preceding example, may have their
// own constructors. The difference from non-abstract classes is that the
// abstract class constructor may only be invoked as a part of delegation call in
// the subclass definition.

abstract class Animal(val name: String)
class Cat : Animal {
    var age: Int
    constructor(name: String) : super(name) {
        this.age = 0
    }
    constructor(name: String, age: Int) : super(name) {
        this.age = age
    }
}

// Another feature of abstract classes allows you to declare abstract members.
// An abstract member defines a basic shape of a function or property such as
// its name, parameters and return type, but omits any implementation details.
// When a non-abstract class inherits such members from its abstract parent,
// they must be overridden and given an implementation.

abstract class Shape { // you can omit empty constructor
    abstract val width: Double
    abstract val height: Double
    abstract fun area(): Double
}
class Circle(val radius: Double) : Shape() {
    val diameter
        get() = 2* radius
    override val width
        get() = diameter
    override val height
        get() = diameter
    override fun area() = PI*radius*radius
}
class Rectangle(
    override val width: Double,
    override val height: Double
) : Shape() {
    override fun area() = width * height
}
fun Shape.print() {
    println("Size: $width*$height, area: ${area()}")
}
fun main() {
    val circle = Circle(10.0)
    circle.print() // Size: 20.0*20.0, area: 314.1592653589793

    val rectangle = Rectangle(3.0, 5.0)
    rectangle.print() // Size: 3.0*5.0, area: 15.0
}

// Note that abstract members are implicitly open, so you don’t need to
// explicitly mark them as such.
