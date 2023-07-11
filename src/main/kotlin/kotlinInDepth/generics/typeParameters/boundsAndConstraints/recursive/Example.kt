package kotlinInDepth.generics.typeParameters.boundsAndConstraints.recursive

// A type parameter bound may refer the type parameter itself in which case
// it’s called recursive. For example, if our tree contains instances of a
// comparable interface, we can find a node with the maximum value

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

fun <T: Comparable<T>> TreeNode<T>.maxNode(): TreeNode<T> {
    val maxChild = children.maxByOrNull { it.data } ?: return this
    return if (this.data >= maxChild.data) this else maxChild
}

fun program1() {
    // Double is subtype of Comparable<Double>
    val doubleTree = TreeNode(1.0).apply {
        addChild(2.0)
        addChild(3.0)
    }
    println(doubleTree.maxNode()) // 3.0 {}
    println(doubleTree.maxNode().data) // 3.0

    // String is subtype of Comparable<String>
    val stringTree = TreeNode("abc").apply {
        addChild("xyz")
        addChild("def")
    }
    println(stringTree.maxNode().data) // xyz
}

// Bounds can also refer to preceding type parameters.

fun <T> TreeNode<T>.walkDepth(action: (T) -> Unit) {
    children.forEach { it.walkDepth(action) }
    action(data)
}

fun <T, U : T> TreeNode<U>.toList(list: MutableList<T>) {
    // Important: U extends T, T extends Any? as by default
    walkDepth { list += it } // OK: we can add child class U objects to list of super class T
}

// Since U is a subtype of T, the preceding function may accept lists of more
// general elements. For example, we can append trees of Int and Double to a
// list of Number:

fun program2() {
    val list = ArrayList<Number>()

    TreeNode(data = 1).apply {
        addChild(2)
        addChild(3)
    }.toList(list)

    TreeNode(data = 1.0).apply {
        addChild(2.0)
        addChild(3.0)
    }.toList(list)

    println(list) // [2, 3, 1, 2.0, 3.0, 1.0]
}

// A particularly common case is constraining the type parameter to be not
// null. To do this, we need to use the non-nullable type as its upper bound:
fun <T: Any> notNullTreeOf(data: T) = TreeNode(data)

fun program3() {
    val nullableStringTree = notNullTreeOf(data = "abc").apply {
//      addChild(null) // Error: Null can not be a value of a non-null type String
    }
}

fun main() {
    program1()
    program2()
}