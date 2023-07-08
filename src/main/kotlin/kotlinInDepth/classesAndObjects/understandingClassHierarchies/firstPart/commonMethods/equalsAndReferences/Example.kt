package kotlinInDepth.classesAndObjects.understandingClassHierarchies.firstPart.commonMethods.equalsAndReferences
import kotlinInDepth.classesAndObjects.understandingClassHierarchies.firstPart.commonMethods.equalsSolution.Address

// The original referential equality is implemented by === and !== operators;
// their behavior, unlike that of == and !=, can’t be overridden in
// the user code. The structure equality is compared using == and !=.
fun program1() {
    val addr1 = Address(city = "London", street = "Ivy Lane", house = "8A")
    val addr2 = addr1 // the same object
    val addr3 = Address(city = "London", street = "Ivy Lane", house = "8A") // different object, but equal
    println(addr1 === addr2) // true
    println(addr1 == addr2) // true
    println(addr1 === addr3) // false
    println(addr1 == addr3) // true
}

// Java vs Kotlin: In Java, on the opposite to Kotlin, == and != operators implement
// referential equality, while content-based is expressed by an explicit call to
// equals(). The latter must also be guarded against a possible null value of
// its receiver object to avoid NPE.

// Just like in Java, a custom implementation of the equals() method must be
// accompanied by a corresponding hashCode(). This is because some collections
// (such as HashSet and HashMap) use hashCode() to find a value in the hash table
// first and then use the equals() method to filter through all candidates
// with the same hash code.
/* Remember some rules:
 1) If objects are equal, their hash codes must be the same;
 2) If objects are not equal, their hash codes do not have to be different.
*/

// Properties are compared by delegating to their own implementation of
// equals() and hashCode(). Array types comprise an exception. Since they
// do not have their own content-based equality implementation, the generated
// code will use contentEquals() and contentHashCode() (or
// contentDeepEquals()/contentDeepHashCode() when applied to
// properties of multidimensional array types).


// Helper code
fun main() {
    program1()
}
