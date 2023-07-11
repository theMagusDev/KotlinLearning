package kotlinInDepth.generics.typeParameters.genericDeclaration.part1

// In order to make a declaration generic, we need to add one or more type
// parameters to it. Such parameters can then be used inside the declaration in
// place of ordinary types.
val map = HashMap<Int, String>()
val list1 = arrayListOf<String>()

// Sometimes, these type arguments can be omitted since
// the compiler can infer them from context
val hashMap: Map<Int, String> = HashMap()
val list2 = arrayListOf("abc", "def")

// Let’s see how to create generic declarations of our own. Suppose we want
// to define a class representing a tree which can store values of a given type:

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

fun main() {
    val root = TreeNode("Hello").apply {
        addChild("World")
        addChild("!!!").addChild(":)")
        addChild("I'm").addChild("string").addChild("tree")
    }
    println(root) // Hello {World {}, !!! {:) {}}, I'm {string {tree {}}}}
}

// Type parameters of a class are written inside angle brackets which
// are put right after the class name. Inside a class type, parameters
// can be used to define types of variables, properties, or functions
// or as argument types for other generic declarations.

// Java vs Kotlin: Kotlin prohibits raw use of generic classes.
// While in Java you can create ArrayList(), in Kotlin you must
// specify its type.

