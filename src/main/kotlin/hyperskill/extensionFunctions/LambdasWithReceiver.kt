package hyperskill.extensionFunctions

// First of all, what is a receiver?
// In the context of extension functions, the receiver is
// an object instance that extends its functionality by the function.
// Receivers can be omitted to give you direct access to the receiver’s members.

fun Int.isEven() = this % 2 == 0
//   ^               ^
// Receiver       Receiver
//  type          (object)

// A lambda with a receiver is a way to define behavior similar
// to an extension function, and it uses lambda expressions
// to operate with some object.

// With lambdas with receivers, you indicate how methods are resolved
// in the lambda body. The receiver is an extension function type.
// It enables the access to the visible methods and properties of
// the receiver of the lambda in its body without any additional qualifier.

val sum: (Int, Int) -> Int = {a, b -> a + b}
fun program4() {
    println(sum(1, 2)) // 3
}

// We can use a lambda with a receiver to rewrite our code.
val newSum: Int.(Int) -> Int = { n -> this + n }
fun program5() {
    println(newSum(1, 2)) // 3
    println(1.newSum(2)) // 3
}

// val function: A.(B) -> C = { body }
//        ^      ^  ^     ^      ^
//       (1)    (2)(3)   (4)    (5)
/*
* 1 - function value;
* 2 - receiver object type, function can be called on it;
* 3 - object's parameter;
* 4 - return type;
* 5 - action to perform (function's body).
 */

// Inside the body of the function literal, you can access the members
// of the receiver object using the expression 'this'.

// Let’s try to generalize this example code to a code block that
// allows us to perform a series of operations with integers
// using a lambda with a receiver.

fun Int.operation(function: Int.() -> Int) = this.function()

fun program6() {
    var res: Int = 10.operation { this * 2 }
    // 'this * 2' becomes a new function extending the Int type. Since
    // it is extending Int, it can refer to the Int's instance
    // using 'this' expression.
    println(res) // 20

    // Use times instead of multiplication symbol '*'
    res = 10.operation { this.times(2) }
    println(res) // 20

    // Omit 'this' expression
    res = 10.operation { times(2) }
    println(res) // 20

    // Use the plus() function
    res = 10.operation { plus(10) }
    println(res) // 20
}

/* Usage of lambdas with receivers */

// Safe Builder String with Lambda with receiver
fun myString(init: StringBuilder.() -> Unit): String {
    return StringBuilder().apply(init).toString()
}
fun program7() {
    val str = myString {
        append("Hello, ".uppercase())
        append("World!")
    }
    println(str) // HELLO, World!
}

// This is the basis of performing DSL operations.
// An example can be seen in apply() scope function.

fun <T> T.apply(block: T.() -> Unit): T {
    block()
    return this
}

data class Student(var name: String, var age: Int)

fun program8() {
    val student = Student("John", 20)
    student.apply {
        this.name = this.name.uppercase()
        this.age += 1
    }
    println(student) // Student(name=JOHN, age=21)
}

// Basically, all 'apply' functions invoke the argument of
// an extension function type on the provided receiver
// and return the receiver itself.

fun main() {
    program4()
    program5()
    program6()
}