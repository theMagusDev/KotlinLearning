package kotlinInDepth.generics.typeParameters.typeErasureAndReification

// There are cases, however, when type parameters can’t
// replace actual types:

/*
fun <T> TreeNode<Any>.isInstanceOf(): Boolean =
    data is T && children.all { it.isInstanceOf<T>() }
            ^
          Error: Cannot check for instance of erased type: T
 */

// The reason is so-called type erasure (подчистка типов)
/*
The JVM information about type arguments is effectively erased
from code, and types like 'List<String>' or 'List<Number>' merge
into the same type 'List'.

So checks like 'data is T' above basically makes no sense:
The isInstance() function just has no way to know
what T means when it’s called.
 */

fun projections() {
    val list = listOf(1, 2, 3) // List<Int>
    println(list is List<Number>) // Warning: Check for instance is always 'true'
//  println(list is List<String>) // Error: Cannot check for instance of erased type: List<String>

    // In some cases, though, the compiler has enough information
    // to ensure that type check is valid.
    val collection: Collection<Int> = setOf(1, 2, 3)
    if (collection is List<Int>) {
        println("list")
    }
    // For example, here the check basically is concerned about the relationship
    // between List and Collection interfaces rather than their particular types
    // such as List<Int> and Collection<Int>.

    // but:
    val anotherCollection: Collection<Number> = setOf(1, 2, 3)
/*
    if (anotherCollection is List<Int>) { // Error: annot check for instance of erased type: List<Int>
        println("list")
    }
*/
    if (anotherCollection is List<*>) { // OK: '*' means some unknown type
        println("list")
    }
}


