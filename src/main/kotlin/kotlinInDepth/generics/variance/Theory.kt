package kotlinInDepth.generics.variance

/*
Variance is an aspect of the generic type which describes how its particular
substitutions are related to each other in terms of subtyping. We’ve already
seen examples of generic types with different variance.

Arrays and mutable collections, for example, do not preserve
subtyping of their arguments. Even though, String is a subtype of Any,
Array<String> is not considered a subtype of Array<Any>.

Immutable collections, like List or Set, on the other hand, do preserve
subtyping, so List<String> is a subtype of List<Any>.
 */

fun main() {
    val objects: List<Any> = List<String>(size = 5) { it.toString() } // OK
//  val objects: Array<Any> = Array<String>(size = 5) { it.toString() } // Error
}