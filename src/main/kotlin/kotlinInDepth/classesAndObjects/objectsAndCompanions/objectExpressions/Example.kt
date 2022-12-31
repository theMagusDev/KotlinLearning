package kotlinInDepth.objectsAndCompanions.objectExpressions

// Kotlin has a special kind of expression which creates
// a new object without an explicit declaration.
// This object expression is very similar to
// a Java anonymous class.

fun main() {
    fun midPoint(xRange: IntRange, yRange: IntRange) = object {
        val x = (xRange.first + xRange.last) / 2
        val y = (yRange.first + yRange.last) / 2
    }

    val myMidPoint = midPoint(1..5, 2..6)
    // val myMidPoint: <anonymous object : Any>

    // the type is anonymous object type which represents a class with
    // all the members defined in the object expression and
    // a single instance. This type is not denotable
    // in the language itself. It’s just an internal representation
    // of the object expression type used by the Kotlin compiler.

    println("${myMidPoint.x}, ${myMidPoint.y}")
}

// An object expression looks just like an object definition
// without a name and being an expression can be, for example,
// assigned to a variable like in the preceding example.

// Note that unlike classes and object expressions, named
// objects can’t be declared inside functions.
/*
fun printMiddle(xRange: IntRange, yRange: IntRange) {
    // Error
    object MidPoint { // Error
        val x = (xRange.first + xRange.last)/2
        val y = (yRange.first + yRange.last)/2
    }
    println("${MidPoint.x}, ${MidPoint.y}")
}
*/

// The rationale behind this decision is that object definitions
// are supposed to represent singletons while local objects, if
// they were allowed, in general would have to be created
// anew upon every call of the enclosing function.

// This example also demonstrates that a function with an
// object expression body has anonymous return type and the same
// is also true for local variables and properties:
fun program() {
    val o = object { // anonymous object type is inferred
        val x = 0
        val y = 0
    }

    println("(${o.y};${o.x})") // can access x and y here
}