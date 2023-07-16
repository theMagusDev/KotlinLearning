package kotlinInDepth.generics.variance.declaration.out.p1

/*
In Kotlin, variance of a type parameter can be specified in two ways: either
in declaration itself, or on its usage site when substituting particular type
arguments. In this section, we’ll focus on the first approach which is called
declaration-site variance.

By default, type parameters are considered invariant:
 */

interface List<T> {
    val size: Int
    fun get(index: Int) : T
}
class ListByArray<T>(private vararg val items: T) : List<T> {
    override val size: Int
        get() = items.size
    override fun get(index: Int): T = items[index]
}

// Suppose we define a function which takes a pair of lists and
// returns their concatenation
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

// Now, everything goes smoothly until we try to use this function to combine
// lists of related types, say, List<Number> and List<T>:
fun main() {
    val numbers = ListByArray<Number>(1, 2.5, 3f)
    val integers = ListByArray<Int>(10, 30, 30)
//  val result = concat(numbers, integers) // Error: required Number, got Int
}

// The reason is an invariance of parameter T: due to that List<Int> is not
// considered a subtype of List<Int> (and vice versa), so we can’t pass a
// List<Int> variable into a function which expects List<Number>.

// See solution in next file

