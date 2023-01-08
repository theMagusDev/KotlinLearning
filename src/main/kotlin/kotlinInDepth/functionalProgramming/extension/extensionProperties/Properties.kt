package kotlinInDepth.functionalProgramming.extension.extensionProperties

// Similarly to functions, Kotlin allows you to define extension properties
// which can be accessed just like any member property.

val IntRange.leftHalf: IntRange
    get() = first..(first + last) / 2

/* Java bytecode (simplified):

public static final IntRange getLeftHalf(IntRange $this$leftHalf) {
    int first = $this$leftHalf.getFirst();
    return new IntRange(first, ($this$leftHalf.getFirst() + $this$leftHalf.getLast()) / 2);
}

 */

fun program1() {
    // Remember some IntRange default properties:
    println((3..6).first) // 3
    println((3..6).last) // 6

    // Use the extension property:
    println((1..3).leftHalf) // 1..2
    println((3..6).leftHalf) // 3..4
}

// The crucial difference between the member and extension property is that
// the extension property can’t have a backing field since there is no
// reliable way to add some extra state to a class instance. It means that
// extension properties can neither have initializers, nor use
// the field keyword inside their accessors. They also can’t be lateinit
// since such properties rely on backing fields.

// For the same reason, an extension property must always have
// an explicit getter and, if mutable, an explicit setter.

val IntArray.midIndex
    get() = lastIndex / 2

var IntArray.midValue
    get() = this[midIndex]
    set(value) {
        this[midIndex] = value
    }

fun program2() {
    val numbers = IntArray(6) { it * it } // 0, 1, 4, 9, 16, 25
    println(numbers.midValue) // 4
    numbers.midValue *= 10
    println(numbers.midValue) // 40
}

// Extension properties, however, can use delegates. Bear in mind, though,
// that the delegate expression can’t access the property receiver.
// So, in general, there is no point in declaring the lazy property
// as an extension since it would have the same value
// for each instance of the receiver type.

val String.message by lazy { "Hello" }
// val String.anotherMessage by lazy { this = "Hello" }
// Error: 'this' is not defined in this context

fun program3() {
    println("Hello".message) // Hello
    println("Bye".message) // Hello
}

// Object definitions can be considered an exception
// since they have only one instance.

object Messages
val Messages.HELLO by lazy { "Hello" }

fun program4() {
    println(Messages.HELLO) // Hello
}

// In general, it’s possible to create a delegate which is able
// to access the property receiver. We’ll see how to do it later.

/* Main */
fun main() {
    program1()
    program2()
    program3()
    program4()
}