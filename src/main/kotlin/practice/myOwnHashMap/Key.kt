package practice.myOwnHashMap

class Key<T>(private val keyValue: T) {

    override fun hashCode(): Int {
        return keyValue.hashCode()
    }
}

class Node<K, V>(
    val key: K,
    val value: V,
    var next: Node<K, V>? = null
)