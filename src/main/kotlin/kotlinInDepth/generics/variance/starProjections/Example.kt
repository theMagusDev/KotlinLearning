package kotlinInDepth.generics.variance.starProjections

import kotlinInDepth.generics.typeParameters.genericDeclaration.part1.TreeNode

/*
Star projections denoted by * are used to indicate that the argument type
can be anything within its bounds. Since Kotlin only supports upper bounds
for type parameters, this amounts to saying that the type argument can be
any subtype of the corresponding bounding type.
 */

val anyList: List<*> = listOf(1, 2, 3) // Element type is bounded by Any?
val anyComparable: Comparable<*> = "abcde" // Type can be any comparable

// In other words, a star projection effectively behaves like an 'out'
// projection applied to a type parameter bound.

/*
Java vs Kotlin: Star projection can be considered a Kotlin counterpart of
Java’s ? wildcard, so TreeNode<*> in Kotlin has basically the same meaning
as TreeNode<?> in Java.

In the section on type erasure and refinement, we’ve seen that
star-projected types can be used in type-checking operations:
 */

fun program1() {
    val any: Any = ""
    val treeNode = TreeNode<Int>(5)

    println(any is TreeNode<*>) // false
    println(any is TreeNode<out Any?>) // false
//  println(any is TreeNode<out Number>) // Error: Cannot check for instance of erased type: TreeNode<out Number>

    println(treeNode is TreeNode<*>) // true
    println(treeNode is TreeNode<out Any?>) // true
}

/*
It’s important to keep in mind the difference between * and using the type
parameter bound as a non-projection argument, like in TreeNode<*> vs TreeNode<Any?>.
While TreeNode<Any?> is a tree which can contain the value of
any type, TreeNode<*> represents the tree whose nodes are characterized by
the same common type T, but that T is unknown to us. For this reason, we
can’t use TreeNode operations which behave like consumers of T values.
Since we don’t know the actual type, we also don’t know what values
are acceptable for them. That’s exactly the meaning of
the out projection we’ve discussed in the previous section.
 */

/*
Note that when the type parameter has more than one bound, * can’t be
replaced with an explicit out projection because the type
intersection is not denotable in the Kotlin source code:
 */
interface Named {
    val name: String
}

interface Identified {
    val id: Int
}

class Registry<T> where T : Named, T : Identified // the bound is intersection of Named and Identified

var registry: Registry<*>? = null

// Another difference between '*' and explicit 'out' is that * are allowed
// for type parameters with the declaration-site variance. In this case,
// the compiler doesn't report warnings/errors:

interface Consumer<in T> {
    fun consume(value: T)
}

interface Producer<out T> {
    fun produce(): T
}

fun program2() {
    val starProducer: Producer<*> // the same as Producer<Any?>
    val starConsumer: Consumer<*> // the same as Consumer<Nothing>

    // Remember: Nothing is a subtype of any class in Kotlin

    /*
    When applied to a type argument in the contravariant position (like in
    Consumer<*>), star projection in fact produces a type argument of Nothing.
    Thus, we can’t pass anything to the consume() function because Nothing
    has no values.
     */
}

fun main() {
    program1()
}
