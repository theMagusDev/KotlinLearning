package kotlinInDepth.classesAndObjects.understandingClassHierarchies.typeCheckingAndCasts

// Now, we can express the smart cast rules more precisely.

/* First, smart casts are not allowed for properties and variables with custom accessors. */

// The reason is that a compiler can’t guarantee that its return value won’t
// change after the check. This also includes properties and local variables
// which use delegates (since they delegate their accessors to other class)

class Holder {
    val o: Any
        get() = "" // custom getter
}

fun program1() {
    val o: Any by lazy { 123 }
    if (o is Int) {
//      println(o*2) // Error: Smart cast to 'Int' is impossible, because 'o' is a property that has open or custom getter
    }

    val holder = Holder()
    if (holder.o is String) {
//      println(holder.o.length) // Smart cast to 'String' is impossible, because 'holder.o' is a property that has open or custom getter
    }
}

// Open member properties also fall into this category since they can be
// overridden in subtypes and given a custom accessor
open class Parent {
    open val o: Any = ""
}
fun program2() {
    val holder = Holder()
    if (holder.o is String) {
//      println(holder.o.length) // Smart cast to 'String' is impossible, because 'holder.o' is a property that has open or custom getter
    }
}

/* Second, smart casts do not work if value is explicitly
changed between the check and the read or if they are modified in some
lambda (since their value may change when lambda is invoked
which in general is unpredictable) */

fun program3() {
    var o: Any = 123
    if (o is Int) {
        println(o + 1) // Smart cast to kotlin.Int
        o = ""
        println(o.length) // Smart cast to kotlin.String
    }
    // BUT
    if (o is String) {
        val f = { o = 123 }
//      println(o.length) // Error: Smart cast to 'String' is impossible, because 'o' is a local variable that is captured by a changing closure
    }
}

fun main() {
    program1()
    program2()
    program3()
}

