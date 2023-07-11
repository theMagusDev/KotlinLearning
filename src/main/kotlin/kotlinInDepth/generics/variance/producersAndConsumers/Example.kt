package kotlinInDepth.generics.variance.producersAndConsumers

/*
Generics classes and interfaces can give rise to an unlimited set of types
produced by substituting different type arguments instead of their type
parameters.

By default, all substitutions of a particular type are not
considered subtypes of each other regardless of relationships between their
arguments. In this case, we say that the generic type is invariant (инвариантен).
For example, the built-in Array class, mutable collection classes as well as
our TreeNode class are all invariant. The following example shows that
TreeNode<String> is not considered a subtype of TreeNode<Any>:
 */
fun program1() {
//  val node: TreeNode<Any> = TreeNode<String>("Hello") // Error
}

/*
Some types, like immutable collections, on the other hand, preserve
subtyping of their arguments. We will see how they do that.

The distinction is based on the way a type handles the values of its type
parameter (say, T). All generic types may be divided into three categories:

 1. Producers which have only operations which return values of T but
never take them as input;
 2. Consumers whose operations only take values of T as input but never
return them;
 3. All remaining types which do not fall into either of the groups above.

It turns out that in general, types from the last group (ones that are neither
producers, nor consumers) can’t preserve subtyping without breaking type
safety. To understand why it happens, let’s consider an example with our
TreeNode class. Suppose for a moment that subtyping is permitted and we
can assign TreeNode<String> to TreeNode<Any>. Consider the following
code:

fun main() {
    val stringNode: TreeNode<String> = TreeNode<String>("Hello")
    val anyNode: TreeNode<Any> = stringNode // copy ref to the stringNode tree above
    anyNode.addChild(123)
    val s = stringNode.children.first()

    // It is possible to add Int child to an original tree of String.
    // If such an assignment is allowed, the program would fail with
    // the exception when trying to cast stringNode.children.first() to String.
}

When we consider type B subtype of type A, we assume that values of B can
be used in any context which requires a value of A. This is clearly not the
case here. The type TreeNode<Any> has an ability to add child nodes of any
type, while TreeNode<String> don’t; it only can add children of type
String. That’s the reason why TreeNode<String> can’t be a subtype of
TreeNode<Any>.

Why immutable collections like List<T> are different? The reason is that
they do not have operations like addChild(): their members only produce
values of T but never consume them. Subtyping of List<String> and List<Any>
does not endanger type safety, and the compiler permits us to make use of
this property. We can say that such types are covariant with respect to their
type argument. All producer-like types can be made covariant in Kotlin.

Many built-in immutable types like Pair, Triple, Iterable, Iterator,
and so on are covariant. On top of that, functional types are covariant with
respect to their return types
 */

fun program2() {
    val stringProducer: () -> String = { "Hello" }
    val anyProducer: () -> Any = stringProducer
    println(anyProducer()) // Hello
}

/*
Note that covariance is not the same as immutability. Covariance (with
respect to T) just forbids taking values of T as input, so it’s possible to have
a mutable type which still can be made covariant. Consider, for example, a
putative list which can only delete its elements by index but can’t add new ones:
 */
interface NonGrowingList<T> {
    val size: Int
    fun get(index: Int): Int
    fun remove(index: Int)
}
// It’s clearly mutable but behaves covariantly: NonGrowingList<String>
// is capable of everything the NonGrowingList<Any> can.

// The reverse is also true. Types representing immutable objects may
// behave non-covariantly. For example:
interface Set<T> {
    fun contains(element: T): Boolean
}
// The preceding type might be immutable, but it’s not a producer and thus
// can’t preserve subtyping. While Set<Any> can take any value as its input,
// Set<String> can take only strings.

/*
What about consumer-like types? They obviously can’t preserve subtyping
in keeping with the preceding arguments. It turns out, though, that they
preserve subtyping in the opposite direction. To understand what it means,
let’s consider two substitutions of the Set<T> type such as Set<Int> and
Set<Number>. The contract of Set<T> can be reduced to the ability to handle
elements of T by the contains() function. So Set<Number> can handle any
Number and Set<Int> can handle any Int. But Int is a subtype of Number,
so Set<Number> can handle any Int as well. In other words, Set<Number>
behaves like a subtype of Set<Int>. In Kotlin, you can, in fact, enable this
subtyping by declaring T contravariant.

Function types, for example, are contravariant with respect to their
argument types:
 */
fun program3() {
    val anyConsumer: (Any) -> Unit = { println(it) }
    val stringConsumer: (String) -> Unit = anyConsumer
    stringConsumer("Hello") // Hello
}

/*
So for a given generic type X<T, ...>, we have the following
options in terms of variance with respect to T:
 1. X behaves like a producer; in this case, we can declare T covariant so
that X<B> will be a subtype of X<A> whenever B is a subtype of A.
 2. X behaves like a consumer; we then can me T contravariant: X<B> will
be a subtype of X<A> whenever A is a subtype of B.
 3. In all remaining cases, T has to remain invariant.
 */


