package kotlinInDepth.generics.variance.withProjections.part2

/*
The TreeNode<T> type has to remain invariant since it contains both
members which can return values of T (like data property) and those which
take T values as their input (like addChild() function), so we can’t use
declaration-site variance here. However, in the context of the addSubtree()
function, a tree we pass as an argument is used exclusively as a producer.
This allows us to achieve our goal by marking the necessary type argument
as out:
 */

class TreeNode<T>(val data: T) {
    private val _children = arrayListOf<TreeNode<T>>()
    var parent: TreeNode<T>? = null
        private set
    val children: List<TreeNode<T>>
        get() = _children
    fun addChild(data: T) = TreeNode<T>(data).also {
        _children += it
        it.parent = this
    }
    override fun toString() =
        _children.joinToString(prefix = "$data {", postfix = "}")
}

fun <T> TreeNode<T>.addSubtree(node: TreeNode<out T>): TreeNode<T> {
    val newNode = this.addChild(node.data)
    node.children.forEach { addSubtree(it) }
    return newNode
}

fun program1() {
    val root = TreeNode<Number>(123)
    val subRoot = TreeNode(456)
    root.addSubtree(subRoot) // OK
}

// Alternatively, we could’ve introduced the additional type parameter
// bounded by the first one to represent elements of the added tree

fun <T, U : T> TreeNode<T>.addSubtreeBounded(node: TreeNode<U>): TreeNode<T> {
    val newNode = this.addChild(node.data)
    node.children.forEach { newNode.addSubtreeBounded(it) }
    return newNode
}

/*
The TreeNode<out T> is called a projected type. The projection out T
means that we do not know the actual type argument of TreeNode: only that
it must be a subtype of T. You can think of TreeNode<out T> as a version of
TreeNode<T> which only exposes operations that act as producers with
respect to T. For example, we can use properties such data, children,
depth, or functions like walkDepthFirst() since they do not take values of
T as their input.

The in-projections can be used similarly to enforce the usage of the type as
a consumer. For example, we could’ve written our tree-adding function in
the following form:
 */

fun <T> TreeNode<T>.addTo(parent: TreeNode<in T>) { // returns Unit
    val newNode = parent.addChild(data)
    children.forEach { it.addTo(newNode) }
}

fun program2() {
    val root = TreeNode<Number>(123)
    val subRoot = TreeNode(456)
    subRoot.addTo(root)
    println(root) // 123 {456 {}}
}

/*
Java vs Kotlin: Kotlin projections play essentially the same role as Java
extends/super wildcards. For example (Kotlin -> Java):
TreeNode<out Number> -> TreeNode<? extends Number>;
TreeNode<in Number> -> TreeNode<? super Number>
 */

interface Producer<out T> {
    fun produce(): T
}
interface Consumer<in T> {
    fun consume(value: T)
}
fun program3() {
//  val inProducer: Producer<in String> // Error: Projection is conflicting with variance
    val outProducer: Producer<out String> // Projection is redundant: the corresponding type parameter of Producer has the same variance
    val inConsumer: Consumer<in String> // Projection is redundant: the corresponding type parameter of Producer has the same variance
//  val outConsumer: Consumer<out String> // Error: Projection is conflicting with variance
}

/*
Kotlin has a special way to denote a generic whose argument can
be replaced by any possible type: a star projection.
 */

fun main() {
    program1()
    program2()
    program3()
}

