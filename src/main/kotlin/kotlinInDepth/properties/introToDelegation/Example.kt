package kotlinInDepth.properties.introToDelegation

interface MyInterface {
    val msg: String
    fun printIt()
}

class MyImplementation : MyInterface {
    override val msg: String = "Hello from MyImplementation!"
    override fun printIt() {
        println(msg)
    }
}

// Nothing new here: the interface declares
// a property and a function, and the class implements those.

/*
* Now, suppose that we need to create a new class 'Cat',
* which would:
* 1) have some functionality of its own
* 2) would implement the given interface at the same time.
* We'd stumble upon copy-pasting the code: we already have
* an implementation of this interface, but we need a different class,
* which, however, would still need to implement this interface.
 */

// That's where the delegate comes into play:
// it turns out that we can happily code our new class,
// and when we need to use the implementation of the interface,
// we just reference the already existing implementation.
class MyNewClass(base: MyInterface) : MyInterface by base {
    // base will provide all the necessary implementations
    override val msg: String = "Hello from MyNewClass!"
}

/*
class MyNewClass(
        base: MyInterface)
        // ^ Here we expect an implementation of MyInterface as a parameter (named "base")
        : MyInterface by base {
        // ^ And here we state that MyInterface is implemented by the previously obtained parameter, the one named "base"
    override val msg = "Delegate sends regards."
}
*/

fun main() {
    // We create an instance of class, implementing MyInterface
    val a = MyImplementation()

    // Then we pass this implementation instance as a parameter
    val delegate = MyNewClass(a)
    println(delegate.msg) // Hello from MyNewClass!
    println(delegate.printIt()) // Hello from MyImplementation!
    // Second output explanation: MyNewClass delegates printIt()
    // to MyImplementation. MyImplementation contains msg "Hello from MyImplementation",
    // so code just prints "Hello from MyImplementation".
}