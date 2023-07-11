package kotlinInDepth.generics.typeParameters.genericDeclaration.part2

open class DataHolder<T>(val data: T)
class StringDataHolder(data: String) : DataHolder<String>(data)
class TreeNode<T>(data: T) : DataHolder<T>(data) {
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

// Note that type parameters are not inherited. You pass them to supertype
// similarly to constructor parameters, so T in TreeNode and T in DataHolder
// are separate declarations.

// Functions and properties defined in generic classes may access their type
// parameters as demonstrated by the preceding addChild() and children
// definitions.

// Additionally, you can make a property or a function generic by
// adding type parameters of its own:
fun <T> TreeNode<T>.addChildren(vararg data: T) {
    data.forEach { this.addChild(it) }
}
fun <T> TreeNode<T>.walkDepth(action: (T) -> Unit) {
    children.forEach { it.walkDepth(action) }
    action(data)
}
val <T> TreeNode<T>.depth: Int
    get() = (children.maxOfOrNull { it.depth } ?: 0) + 1

fun main() {
    val root = TreeNode("Hello").apply {
        addChildren("World", "!!!")
    }
    println(root.depth)
}


