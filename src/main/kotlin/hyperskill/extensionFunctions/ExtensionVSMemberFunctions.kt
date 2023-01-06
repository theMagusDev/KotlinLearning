package hyperskill.extensionFunctions

class A {
    fun member() = println("Hi from member function")
}

fun main() {
    fun A.extension() = println("Hi from extension function")

    A().member()
    A().extension()
    // they can be called in the same way.

    // And what if a developer tries to add an already existing function
    // to a class? The answer is a bit complex here because there can be
    // several cases.

    // If we try to define another fun A.extension(), the code won't compile.
    // fun A.extension() = println("Hi from extension function") // Error

    // But if we do so:
    fun A.member() = println("Hi from member hiding the original member")
    // the code will compile, but the call gives us this text:
    // "Hi from member function"

    // Extension is shadowed by a member: public final fun member(): Unit

    // So member functions always win. It helps when somebody wants
    // to change the object behavior intentionally or unintentionally.
}

/* Idiom:
* The extension function is an idiomatic way to add some functionality
* to an existing class. It is simple to use and shows that your new function
* is closely related to the class.
 */
