package kotlinInDepth.funcitonalProgramming.callableReferences

/* Constructor callable reference */

fun program4() {
    class Person(val firstName: String, val lastName: String)

    val createPerson = ::Person
    createPerson("John", "Doe")
}

/* Bound callable reference */

// You can use it to refer to a member function
// in a context of a given class instance:

fun program5() {
    class Person(val firstName: String, val lastName: String) {
        fun hasNameOf(name: String) =
            name.equals(firstName, ignoreCase = true)
    }

    val isJohn = Person("John", "Doe")::hasNameOf
    println(isJohn("JOHN")) // true
    println(isJohn("Jake")) // false
}

// Note that callable references by themselves are not able to distinguish
// between overloaded functions. You have to provide an explicit type if the
// compiler is not able to choose a particular overload.

fun program6() {
    fun max(a: Int, b: Int) = if (a > b) a else b
    fun max(a: Double, b: Double) = if (a > b) a else b

    val f: (Int, Int) -> Int = ::max // OK
    // val g = ::max // Error: ambiguous reference. Both functions match.
}

fun program7(){
    // The ability to specify a particular function signature in a callable reference
    // may be added in a future version of Kotlin. For that reason, using
    // parentheses after the callable reference is currently reserved to
    // accommodate a possible refinement of syntax. If you want to use a callable
    // reference in a call, you have to enclose it in parentheses.

    fun max(a: Int, b: Int) = if (a > b) a else b

    println((::max)(1, 2))
    // println(::max(1, 2)) // Error: this syntax is reserved for future use
}

/* Main method */
fun main() {
    program4()
    program5()
}