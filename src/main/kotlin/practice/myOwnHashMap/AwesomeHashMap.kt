package practice.myOwnHashMap

class AwesomeHashMap<K, V>(
    private val size: Int = 16,
    private val loadFactor: Float = 0.75f
) {
    private val array = Array<Node<K, V>?>(16) {
        null
    }

    fun put(key: K, value: V) {
        if (checkResize()) resize()

        val keyClass = Key(keyValue = key)
        val hashCode = key.hashCode()
        val size = array.size
        val index = (array.size - 1).and(hashCode)
        println("HashCode: $hashCode, Index: $index")

        if (index >= array.size) throw IllegalStateException("Array index out of bounds")

        if (array[index] == null)
            array[index] = Node(key = key, value = value)
        //else
            // Тут нужно сделать как в блокноте
    }

    fun get (key: K): V? {
        val keyClass = Key(keyValue = key)
        val hashCode = key.hashCode()
        val size = array.size
        val index = (array.size - 1).and(hashCode)

        if (index >= array.size) return null

        return array[index]?.value;
    }

    private fun resize() {
        val newArray = Array<Node<K, V>?>(array.size * 2) {
            if (it < array.size) array[it] else null
        }
    }

    private fun checkResize(): Boolean {
        return array.filterNotNull().size.toFloat() / array.size.toFloat() >= loadFactor
    }
}