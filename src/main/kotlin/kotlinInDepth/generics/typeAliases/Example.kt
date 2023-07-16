package kotlinInDepth.generics.typeAliases

/*
The idea of type aliases is allowing you to introduce alternative names
for existing types. The primary goal of such a construct is to provide
short names for otherwise long types such as generic or functional ones:
 */

typealias IntPredicate = (Int) -> Boolean
typealias IntMap = HashMap<Int, Int>

// Now, we can use the preceding names instead of the right-hand sides of
// their definitions:

fun readFirst(filter: IntPredicate) =
    generateSequence { readlnOrNull()?.toIntOrNull() }.firstOrNull(filter)
// instead of:
fun oldReadFirst(filter: (Int) -> Boolean) =
    generateSequence { readlnOrNull()?.toIntOrNull() }.firstOrNull(filter)

fun program1() {
    val map = IntMap().also {
        it[1] = 2
        it[2] = 3
    }
    // instead of
    val oldMap = HashMap<Int, Int>().also {
        it[1] = 2
        it[2] = 3
    }
}

// One more useful case is providing short names for nested classes:

sealed class Status {
    object Success : Status()  {
        fun allOk() {
            println("All OK!")
        }
    }
    class Error(val message: String) : Status() {
        fun report() {
            println("Error $message occurred.")
        }
    }
}
typealias StSuccess = Status.Success
typealias StError = Status.Error

fun program2() {
    val success = StSuccess
    success.allOk() // All OK!

    val error = StError("404: Page not found")
    error.report() // Error 404: Page not found occurred.
}

// Similar to classes, type aliases may have type parameters which
// allow us to introduce aliases for generic types:
typealias ThisPredicate<T> = T.() -> Boolean
typealias MultiMap<K, V> = Map<K, Collection<V>>

// You may also restrict their scope by using visibility modifiers:
private typealias MyMap = Map<String, String>

// As of now type aliases may only be introduced at top-level.
// For example, it’s not possible to declare them inside functions
// or as class members:

fun program3() {
//  typealias intArrayList = ArrayList<Int> // Error: Nested and local type aliases are not supported
}

// Another restriction is that you can’t declare bounds or constraints
// for type parameters of generic type alias:

// typealias ComparableMap<K : Comparable<K>, V> = Map<K, V> // Error: Bounds are not allowed on type alias parameters

/*
The important thing to note is that type aliases never introduce new types,
just give an additional way to refer to existing ones. This, in particular,
means that type aliases are completely interchangeable with their original types:
 */
typealias A = Int
fun program4() {
    val integer = 1
    val a: A = integer
    val b: Int = a
}

fun main() {
    program1()
    program2()
    program3()
    program4()
}
