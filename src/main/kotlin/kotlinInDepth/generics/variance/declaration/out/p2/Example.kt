package kotlinInDepth.generics.variance.declaration.out.p2

/*
This is, however, too restrictive. A quick glance at the List interface
reveals that it actually behaves like a producer type. Its operations only
return values of T but never take them as input. In other words, this type can
be safely made covariant. To do this, we mark parameter T with the out keyword:
 */
interface List<out T> {
    val size: Int
    fun get(index: Int): T
}

// Now, the concat() call works as expected because the compiler
// understands that List<Int> is a subtype of List<Number>.

fun <T> concat(list1: List<T>, list2: List<T>) = object : List<T> {
    override val size: Int
        get() = list1.size + list2.size
    override fun get(index: Int): T {
        return if (index < list1.size) {
            list1.get(index)
        } else {
            list2.get(index - list1.size)
        }
    }
}

// we can also use out here since it is producer
class ListByArray<out T>(private vararg val items: T) : List<T> {
    override val size: Int
        get() = items.size
    override fun get(index: Int): T = items[index]
}

fun main() {
    val numbers = ListByArray<Number>(1, 2.5, 3f)
    val integers = ListByArray<Int>(10, 30, 30)
    val result = concat(numbers, integers) // OK
}

/*
Note, that we compiler will not allow you to mark class as
producer if it is not so:
 */
interface MutableList<out T> : List<T> {
//  fun set(index: Int, value: T)
    //                         ^
    //               Error: Type parameter T is declared as 'out' but occurs in 'in' position in type T
}

// It happens because of the set function which takes an input
// value of T, thus acting as its consumer.

