package kotlinInDepth.properties.lazyProperties

import java.io.File

val text: String by lazy {
    // in this block specify how it is initialized
    File("data.txt").readText()
}

// the value 'text' is not computed until we first
// access it in main(). After initialization,
// the property value is stored in a field and
// all successive attempts to access it will just read the
// stored value.

fun program1() {
    while (true) {
        when (val command = readLine() ?: return@program1) {
            "print data" -> println(text) // after this line 'text' has initialized String value
            "exit" -> return@program1
        }
    }
}

/*
* If we have, for example, define a property with
* a simple initializer:

// val text = File(“data.txt”).readText()

* The file would be read right at the program start
* while the property with a getter looks like this:

val text get() = File("data.txt").readText()

* would reread the file every time the program tries
* to access the property value. So lazy is better here.
 */

/*
* This syntax is in fact a special case of the so called delegated property
which allows you to implement a property via a special delegate object
which handles reading/writing and keeps all related data if necessary. The
delegate is placed after the by keyword and can be an arbitrary expression
which returns the object conforming to specific convention. In our example,
lazy {} is not a built-in language construct, but rather just a call to a
standard library function with a lambda supplied.
 */

// Note that unlike lateinit properties,
// lazy properties may not be mutable because
// they can be initialized only once.
// var text1 by lazy { "Hello" } // Error

// By default, lazy properties are thread-safe. In a multithreaded
// environment, the value is computed by a single thread and
// all threads trying to access a property will ultimately get
// the same result.

// Since Kotlin 1.1, you can use delegates for local variables.
fun longComputation(): Int { Thread.sleep(5000); return 1 }
fun program2(args: Array<String>) {
    val data by lazy { longComputation() } // lazy local variable
    val name = args.firstOrNull() ?: return@program2
    println("$name: $data") // data is only accessed when name is not null
}

// Note that delegated properties currently do not support smart casts.
// Since delegates may have an arbitrary implementation, they
// are treated similarly to properties with custom accessors.
// It also means that you can use smart
// casts with local delegated variables:
fun program3() {
    val data by lazy { readLine() }
    if (data != null) {
        // No smart cast, data is nullable here

        // println("Length: ${data.length}")
        // Smart cast to 'String' is impossible,
        // because 'data' is a property that has open or custom getter
    }
}

// Lazy properties/local variables are not an exception.
// Currently, you can’t apply smart casts to them even though
// their values do not actually change after initialization.


/* main function */
fun main(args: Array<String>) {
    program1()
    program2(args)
}