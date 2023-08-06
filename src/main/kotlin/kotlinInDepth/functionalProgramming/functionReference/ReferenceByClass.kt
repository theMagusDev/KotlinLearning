package kotlinInDepth.functionalProgramming.functionReference

// Also, you can reference functions that belong to a class.
// The base syntax in such cases looks like this:

//objectOrClass::functionName

class Person(val name: String, val lastname: String) {

    fun printFullName(): String {
        return("full name: $name $lastname")
    }
}

fun main() {
    val person = Person("Sara", "Rogers")
    val personFun: () -> String = person::printFullName

    println(personFun.invoke())
    println(personFun())
    println(person::printFullName)

    // full name: Sara Rogers
}