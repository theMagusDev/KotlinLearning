package kotlinInDepth.objectsAndCompanions.objectExpressions

// Note, however, that anonymous types are only propagated to
// local or private declarations. If we, for example, were to declare
// the midPoint() function as a top-level one, we would get
// a compile-time error on attempt to access object members.

fun midPoint(xRange: IntRange, yRange: IntRange) = object {
    val x = (xRange.first + xRange.last) / 2
    val y = (yRange.first + yRange.last) / 2
} // the return type is Any, not <anonymous object : Any>

// Now the return type of the midPoint() function is not an anonymous type
// of our object expression, but rather it’s a denotable supertype.
// Since our object has no explicit supertype, it’s assumed to be Any.
// That’s why midPoint.x reference becomes unresolved.

fun main() {
    val myMidPoint = midPoint(1..5, 2..6)
    // val myMidPoint: Any
    // instead of myMidPoint: <anonymous object : Any>
    // println("${myMidPoint.x}, ${myMidPoint.y}") // Error: x and y are unresolved

}
