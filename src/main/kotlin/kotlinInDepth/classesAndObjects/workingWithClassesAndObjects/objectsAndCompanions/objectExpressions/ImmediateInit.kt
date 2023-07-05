package kotlinInDepth.objectsAndCompanions.objectExpressions

// Note that unlike object declarations which are initialized lazily,
// object expressions are initialized immediately after their instance
// is created.

// For example, the following code will print “x = 2”
// since at the point, the x variable is read, the initialization code
// in the object expression has already been executed:

fun main() {
    var x = 1
    val o = object {
        val a = x++
    }

    println("o.a = ${o.a}") // o.a = 1
    println("x = $x") // x = 2
}