package kotlinInDepth.classesAndObjects.understandingClassHierarchies.firstPart.commonMethods.toString

// Similar to Java, all Kotlin classes have a toString() method which
// provides the default String representation of a given instance. By default,
// such a representation is composed of a class name and an object’s hash
// code, so in most cases it’s worth overriding to get more readable
// information.

class Address(
    val city: String,
    val street: String,
    val house: String
) {
    override fun toString() = "$city, $street, $house"
}
open class Entity(
    val name: String,
    val address: Address
)
class Person(
    name: String,
    address: Address,
    var age: Int
) : Entity(name, address) {
    override fun toString() = "$name, $age at $address"
}
class Organization(
    name: String,
    address: Address,
    val manager: Person?
) : Entity(name, address) {
    override fun toString() = "$name at $address"
}

fun main() {
    println(
        Person(
            name = "John Doe",
            address = Address("London", "Baker Street", "9A"),
            age = 25
        )
    ) // John Doe, 25 at London, Baker Street, 9A
    println(
        Organization(
            name = "Orion Inc.",
            address = Address("Moscow", "Prechistenskiy Pereulok", "17"),
            manager = null
        )
    ) // Orion Inc. at Moscow, Prechistenskiy Pereulok, 17
}

