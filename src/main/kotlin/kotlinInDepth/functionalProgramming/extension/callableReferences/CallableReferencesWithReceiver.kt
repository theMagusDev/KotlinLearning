package kotlinInDepth.functionalProgramming.extension.callableReferences

fun aggregate(numbers: IntArray, operation: Int.(Int) -> Int): Int {
    var result = numbers.firstOrNull()
        ?: throw IllegalArgumentException("Empty array")
    for (i in 1..numbers.lastIndex)
        result = result.operation(numbers[i])
    return result
}
fun Int.max(other: Int) = if (this > other) this else other
fun program1() {
    val numbers = intArrayOf(1, 2, 3, 4)
    println(aggregate(numbers, Int::plus)) // 10
    println(aggregate(numbers, Int::max)) // 4
}

// Thanks to implicit casting between extension and non-extension functional
// types we’ve mentioned in the previous section, it’s also possible to use
// non-receiver callable references in the context where the functional type
// with the receiver is expected. For example, we could’ve passed a
// two-argument callable reference ::max for a
// parameter of type Int.(Int) -> Int.

fun program2() {
    fun max(a: Int, b: Int) = if (a > b) a else b

    println(aggregate(intArrayOf(1, 2, 3, 4), ::max)) // 4
}

// The converse is true as well. Callable references with the receiver can be
// used when the expected functional type is a non-receiver one. In a slightly
// modified example, callable references to member and extension functions
// are used as values of a two-argument functional type (Int, Int) -> Int:

fun oldAggregate(numbers: IntArray, operation: (result: Int, number: Int) -> Int): Int {
    var result = numbers.firstOrNull()
        ?: throw IllegalArgumentException("Empty array")
    for (i in 1..numbers.lastIndex)
        result = operation(result, numbers[i])
    return result
}

// See line #10: fun Int.max(other: Int) = if (this > other) this else other

fun program3() {
    println(oldAggregate(intArrayOf(1, 2, 3, 4), Int::max)) // 4
    println(oldAggregate(intArrayOf(1, 2, 3, 4), Int::plus)) // 10
}

// Remember, that it is possible because compiler rebuilds an extension function
// into static function having the receiver's type object as first parameter
// and other parameters after it as they were in the extension function.

// See 'extensions/extensionFunctions/UnderTheHood.kt' for more details.

// Note that callable references are not supported for extensions functions
// declared as class members as currently there is no way to specify multiple
// receiver types for a :: expression.

// MyClass.kt

class MyClass {
    fun String.coolStringExtension() = "Cool $this" // is an extension function,
    // but actually is MyClass's member function which behaves like an
    // extension function with receiver String.

    // val bar = String::coolStringExtension
    // Error: 'coolStringExtension' is a member and an extension
    // at the same time. References to such elements are not allowed.
}

/* Java bytecode:
public final class MyClass {

   public final String coolStringExtension(String $this$coolStringExtension) {
      return "Cool " + $this$coolStringExtension;
   }
}
 */

fun main() {
    program1()
    program2()
    program3()
}
