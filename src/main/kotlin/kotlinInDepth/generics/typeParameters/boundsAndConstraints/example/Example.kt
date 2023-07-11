package kotlinInDepth.generics.typeParameters.boundsAndConstraints.example

// By default, type parameters do not impose any restrictions on their values
// and behave as if they are synonymous to Any? type. Sometimes, though,
// implementation of the generic class, function, or property requires some
// additional information about the data they manipulate.

// Suppose that we want to define a function which
// computes an average value among all tree nodes. Such an operation is
// applicable to numeric trees so we want the type element to be a
// subtype of Number. In order to do this, we declare a type parameter
// with Number as upper bound:

// Old code
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
fun <T> TreeNode<T>.walkDepth(action: (T) -> Unit) {
    children.forEach { it.walkDepth(action) }
    action(data)
}
fun <T: Number> TreeNode<T>.average(): Double {
    var counter = 0
    var sum = 0.0
    this.walkDepth {
        counter++
        sum += it.toDouble() // 'it' extends Number
    }
    return sum / counter
}

// When the type parameter has an upper bound, the compiler will check
// whether corresponding type arguments are subtypes of that bound.
// By default, the upper bound is assumed to be Any?

fun main() {
    val treeNode = TreeNode<Double>(1.5).apply {
        addChild(4.5)
        addChild(3.0).addChild(9.0)
    }
    println(treeNode.average()) // OK: 4.5 (Double is subtype in Number)

    val stringTreeNode = TreeNode<String>("Hello").apply {
        addChild("world")
        addChild("!!!")
    }
//  println(stringTreeNode.average()) // Error: Unresolved reference

    val intTreeNode = TreeNode(1).apply {
        addChild(2).addChild(3)
        addChild(4).addChild(5)
    }
    println(intTreeNode.average()) // OK: 3.0 (Int is subtype in Number)
}

