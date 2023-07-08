package kotlinInDepth.classesAndObjects.understandingClassHierarchies.firstPart.commonMethods.equalsSolution

class Address(
    val city: String,
    val street: String,
    val house: String
) {
    // Solution: override equals() function

    override fun equals(other: Any?): Boolean {
        if (other !== this) {
            return other is Address && this.city == other.city && this.street == other.street && this.house == other.house
            // Smart cast to kotlinInDepth.classesAndObjects.understandingClassHierarchies.commonMethods.equalsSolution.Address
            // after checking 'other is Address' because of lazy conjunction
        } else {
            return true
        }
    }

    // Remember: if you override equals(), you should to override hashCode() too
    override fun hashCode(): Int {
        var result = city.hashCode()
        result = 31 * result + street.hashCode()
        result = 31 * result + house.hashCode()
        return result
    }

    // Note that only properties chosen for equals() can be used in hashCode().
    // This ensures that equal objects always have the same hash code.
}
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

// Now, the index() call from the preceding example finds
// our Address object and returns 2.
fun main() {
    val addresses = arrayOf(
        Address("London", "Ivy Lane", "8A"),
        Address("New York", "Kingsway West", "11B"),
        Address("Sydney", "North Road", "129")
    )
    println(addresses.indexOf(Address("Sydney", "North Road", "129"))) // 2
}

// Note that the equals() method is commonly used in its operator form ==
// or !=. These operators may also be applied to nullable values. When the left
// operand is null, they simply compare the right one with null referentially.