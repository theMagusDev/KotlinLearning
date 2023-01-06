package kotlinInDepth.functionalProgramming.callableReferences

// The callable reference can also be constructed for Kotlin properties.
// Such references, however, are not functional values by themselves,
// but rather reflection objects containing the property information.

// Using the getter property, we can access the functional value
// corresponding to the getter function. For a var declaration,
// the setter property similarly allows you to refer to setter:

class Person(var firstName: String, var lastName: String)

fun main() {
    val person = Person("John", "Doe")
    val readName = person::firstName.getter
    val writeLastName = person::lastName.setter // bound reference to setter

    println(readName()) // invoke the getter
    // Output: John

    writeLastName("Smith") // invoke the setter
    println(person.lastName) // Smith
}

// Callable references to local variables are currently not supported,
// but may be added in a future version.

// Important note: On top of that, a callable reference is not just
// a functional value but also a reflection object which you can use
// to obtain the function or property attributes at runtime.
// In Chapter 10, Annotations and Reflection, we’ll address
// the reflection API in more detail.
