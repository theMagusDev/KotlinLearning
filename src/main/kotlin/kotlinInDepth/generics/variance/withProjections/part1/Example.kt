package kotlinInDepth.generics.variance.withProjections.part1

/*
Another way to specify a variance is to place the out/in keyword before a
type argument in a particular usage of generic type. This construct, also
called a projection, is useful for types which are invariant in general, but can
be used as either producers, or consumers depending on the context.
 */

// Suppose if we want to implement a function which adds a copy of existing
// tree to another tree as a child. Let’s start with invariant definition:

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

fun <T> TreeNode<T>.addSubtree(node: TreeNode<T>): TreeNode<T> {
    val newNode = this.addChild(node.data)
    node.children.forEach { newNode.addSubtree(it) }
    return newNode
}

// This function works well when both trees have the same type:
fun program1() {
    val root = TreeNode("abc") // Compiler infer <String>
    val subRoot = TreeNode("def")
    root.addSubtree(subRoot)
    println(root) // abc {def {}}
}

// But what if we want to, say, add a tree of Int to a tree of Number? This
// operation is actually well-defined since Int is a subtype of Number and
// adding Int-based nodes to a Number tree does not violate any assumptions
// about its type. But since TreeNode<T> is invariant and we’ve specified that
// both trees have the same element type T, the compiler won’t let us do it:
fun program2() {
    val root = TreeNode<Number>(123)
    val subRoot = TreeNode(456)
//  root.addSubtree(subRoot) // Error: Type mismatch. Required: 'Int' Found: 'Number'
}

// See solution in next file

fun main() {
    program1()
    program2()
}
