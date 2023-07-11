package kotlinInDepth.generics.typeParameters.typeErasureAndReification
import kotlinInDepth.generics.typeParameters.genericDeclaration.part1.TreeNode
import java.util.*

/*
In Java, you have to rely on casts or use reflection to work around
type erasure. Both approaches have their drawbacks since casts may mask a
problem and leaf to error afterwards, while using the reflection API may
impact performance.

Kotlin, however, offers you a third option which doesn't
suffer from neither of these weaknesses: reification (материализование).

Reification means that the type parameter information is retained at
runtime. How a compiler does circumvent type erasure? The answer is that
reified type parameters are only available for inline functions. Since the
function body is inlined at the call site where type arguments are provided,
the compiler always knows which actual type corresponds to type
parameters in a particular inlined call.

To make the parameter reified, we need to mark it with a corresponding
keyword.
 */

fun <T> TreeNode<T>.cancellableWalkDepth(
    onEach: (T) -> Boolean
): Boolean {
    val nodes = Stack<TreeNode<T>>() // Remember: Stack follows LIFO
    nodes.push(this) // Pushes an item onto the top of this stack
    while (nodes.isNotEmpty()) {
        val node = nodes.pop()
        if (!onEach(node.data)) return false
        node.children.forEach { nodes.push(it) }
    }
    return true
}
inline fun <reified T> TreeNode<*>.isInstanceOf() = cancellableWalkDepth { it is T }

fun main() {
    val tree = TreeNode<Any>("abc").apply {
        addChild("def")
        addChild(123)
    }
    println(tree.isInstanceOf<String>())
    // The compiler will inline isInstanceOf() substituting the actual type
    // String instead of T, and the code that gets executed will look like this:
    println(tree.cancellableWalkDepth { it is String })
}

/*
Note, however, that using the inline function tends to increase the size of the
compiled code, but this issue can be mitigated by extracting heavy portions
of code into separate non-inline functions (like we did with cancellableWalkDepth()).

Reified type parameters still have their own limitations which distinguish
them from full-fledged types. In particular, it’s currently not possible to call
the constructor or access companion members via reified type parameters:
 */

// inline fun <reified T> factory() = T() // Error