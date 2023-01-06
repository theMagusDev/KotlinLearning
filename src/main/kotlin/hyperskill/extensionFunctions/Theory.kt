package hyperskill.extensionFunctions

// To work with classes that you can't modify, you can write a function
// that takes an object of that class as an argument:
fun higherOrderRepeated(string: String): String = string + string

fun program1() {
    higherOrderRepeated("ha") // haha

    // Do you see an issue here? In Kotlin, standard operations are available
    // as member functions. For example, to get the first symbol of the "ha"
    // string, you can write "ha".first(). It's more convenient to use
    // a single syntax for all operations. So how can we add a member function
    // to the uneditable String class?
}

// Kotlin has just the right syntactic sugar for it: extension functions.
// Let's rewrite the repeated function as an extension one:
fun String.repeated(): String = this + this
fun program2() {
    // Now to get the "haha" we can write
    println("ha".repeated())
}

// The class to be extended is called a receiver type.

// You can get access to the field of an object in the extension function
// almost as easily, as in the member function. That object is called
// a receiver object.

class Client(val name: String, val age: Int)

fun program3() {
    fun Client.getInfo() = "$name $age" // Client is the receiver type

    val client = Client("John", 32)
    println(client.getInfo()) // client is the receiver object
}

// Note, if the developer hides some information
// (you will know how to do it later) and your code cannot get it,
// the extension function can't have access too. So, it works more like
// a simple function, not a member function.

/* Main fun */
fun main() {
    program1()
    program2()
    program3()
}