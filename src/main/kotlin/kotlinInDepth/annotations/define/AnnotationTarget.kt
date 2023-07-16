@file:Suppress("SENSELESS_COMPARISON") // target a whole file (MUST be at the first line)
package kotlinInDepth.annotations.define

/*
Annotation targets are all kinds of annotated elements, such as classes,
functions, properties, and expressions. Some annotations can work with
specific elements, and some can work with all elements.
 */

fun program3() {
    if (5 > 7) return // OK thanks to line #1
}

fun program4() {
    var number = 45 // Warning: Variable is never modified, so it can be declared using 'val'
    var anotherNumber = 5 // Warning: Variable is never modified, so it can be declared using 'val'
    println(number)
    println(anotherNumber)
}

fun program5() {
    @Suppress("CanBeVal") // target field
    var number = 45 // OK
    var anotherNumber = 5 // Warning: Variable is never modified, so it can be declared using 'val'
    println(number)
    println(anotherNumber)
}

@Suppress("CanBeVal") // target function
fun program6() {
    var number = 45 // OK
    var anotherNumber = 5 // OK
    println(number)
    println(anotherNumber)
}

@Suppress("CanBeVal") // target class
class MyClass {
    fun printNumber() {
        var number = 45 // OK
        println(number)
    }

    fun printAnotherNumber() {
        var anotherNumber = 5 // OK
        println(anotherNumber)
    }
}



fun main() {

}
