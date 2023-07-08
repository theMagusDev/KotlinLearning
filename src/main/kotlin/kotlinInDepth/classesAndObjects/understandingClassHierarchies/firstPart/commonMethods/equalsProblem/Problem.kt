package kotlinInDepth.classesAndObjects.understandingClassHierarchies.firstPart.commonMethods.equalsProblem

// Remember: The kotlin.Any class is a root of the Kotlin class hierarchy.
// Every other class is its direct or indirect inheritor.

class Address(
    val city: String,
    val street: String,
    val house: String
)
open class Entity(
    val name: String,
    val address: Address
)
class Person(
    name: String,
    address: Address,
    var age: Int
) : Entity(name, address)
class Organization(
    name: String,
    address: Address,
    val manager: Person
) : Entity(name, address)

// By default, these classes implement only referential equality
// inherited from the Any class.
fun main() {
    val addresses = arrayOf(
        Address("London", "Ivy Lane", "8A"),
        Address("New York", "Kingsway West", "11B"),
        Address("Sydney", "North Road", "129")
    )
    println(addresses.indexOf(Address("Sydney", "North Road", "129"))) // -1
}
