package kotlinInDepth.functionalProgramming.inlineFunctionsAndProperties

// Kotlin's functions and properties can be inlined meaning that
// their body gets substituted in place of their calls.

// In simple words: use inline before higher-order fun when:
// 1) The function passed as argument is used only to invoke itself;
// 2) This higher-order function is not big.

// Using the inline keyword can improve performance only with functions
// that take lambdas as parameters.


/* Detailed explanation from https://stackoverflow.com/questions/44471284/when-to-use-an-inline-function-in-kotlin */
// lines: 64-131

// Let's say you create a higher order function that takes a lambda
// of type () -> Unit (no parameters, no return value),
// and executes it like so:

fun nonInlined(block: () -> Unit) {
    println("before")
    block()
    println("after")
}

/* In Java parlance, this will translate to something like this (simplified!):

* public void nonInlined(Function block) {
*     System.out.println("before");
*     block.invoke();
*     System.out.println("after");
* }


And when you call it from Kotlin...

* nonInlined {
*     println("do something here")
* }

Under the hood, an instance of Function will be created here,
that wraps the code inside the lambda (again, this is simplified):

nonInlined(new Function() {
    @Override
    public void invoke() {
        System.out.println("do something here");
    }
});

So basically, calling this function and passing a lambda to it
will always create an instance of a Function object.
*/

// Use of inline //

// On the other hand, if you use the inline keyword:

inline fun inlined(block: () -> Unit) {
    println("before")
    block()
    println("after")
}

/* When you call it like this:

inlined {
    println("do something here")
}

No Function instance will be created, instead, the code around the invocation
of block inside the inlined function will be copied to the call site,
so you'll get something like this in the bytecode:

System.out.println("before");
System.out.println("do something here");
System.out.println("after");

In this case, no new instances are created. Result: better performance.
 */

// Using higher-order functions and functional values is fraught with certain
// performance overheads, since each function is represented as an object.

// Kotlin, however, provides a solution which can reduce runtime penalties
// of using functional values. The basic idea is to inline a
// higher-order function at its usage replacing a call with a copy of its body.

// Suppose, for example, the function which searches a value
// in an integer array given a predicate, it must satisfy:
inline fun indexOf(numbers: IntArray, condition: (Int) -> Boolean): Int {
    for (i in numbers.indices) {
        if (condition(numbers[i])) return i
    }
    return -1
}
fun program1() {
    println(indexOf(intArrayOf(5, 4, 3, 2, 1)) {it < 3}) // 3
}

// Since the indexOf() function is inlined, the compiler will substitute
// its body instead of the function call. It means that the program1() function
// will be basically equivalent to the code:
/*
fun program1() {
    val numbers = intArrayOf(4, 3, 2, 1)
    var index = -1
    for (i in numbers.indices) {
        if (numbers[i] < 3) {
            index = i
            break
        }
    }
    println(index)
}
 */

// Although inline functions can increase the size of
// the compiled code, when used reasonably they can boost
// performance especially when a function in question is
// relatively small. Many higher-order functions provided by
// the Kotlin standard library are inline.

// The preceding example demonstrates that the inline modifier
// affects not just a function it’s applied to, but also functional values
// which serve as its parameters.

// Since inlined lambdas won’t exist as a separate object at runtime,
// they can’t be, for example, stored in a variable or passed
// to a non-inline function.

var lastAction: () -> Unit = { println("Lambda!") }
var action2 = lastAction

inline fun runAndMemorize(action: () -> Unit) {
    action() // OK
    // lastAction = action // Error
    // println(action) // Error
    // measureExecutionTime(action) // Error

    // because there must be an object that contains this code.
}

// For the same reason, it’s not allowed to inline values
// of a nullable functional type:
/*
inline fun forEach(array: IntArray, action: ((Int) -> Unit)?) { // Error
    if (action == null) return
    for (n in array) action(n)
}
*/

// In such cases, we can forbid inlining of a particular lambda argument
// by marking it with the noinline modifier:
inline fun forEach(array: IntArray, noinline action: ((Int) -> Unit)?) { // OK
    if (action == null) return
    for (n in array) action(n)
}

// Note that when a function has no inlinable parameters, it’s usually not
// worth inlining at all since substituting its body at call site
// will unlikely make a significant difference at runtime.

// What if we try to use private members in a public inline function?
// Since the body of the inline function is substituted instead of a call,
// it might allow some external code to break encapsulation. To avoid this,
// Kotlin forbids references to private members which may be leaked
// to the external code:
class Person(private val name: String, private val lastName: String) {
    inline fun sendMessage(message: () -> String) {
        // println("$name $lastName: ${message()}") // Error
        // Because this is just a block of code, not the Function object.
        // Any class can use this block of code and access private values,
        // which breaks the encapsulation.
    }
}

// Note that if we’d marked the sendMessage() function or its containing
// class with the private modifier, the code would’ve compiled since
// references to private members in the sendMessage() body wouldn’t have
// leaked outside the Person class.

// Kotlin supports inlining of property accessors.
// This may be useful for improving the performance of reading/writing a
// property by eliminating a function call (the property must not have a backing field,
// since this is the case when there will be no variable generated, just function. So
// we are telling to compiler to inline this function):
class Human(var name: String, var lastName: String) {
    var fullName
        inline get() = "$name $lastName" // Inline getter
        set(value) {  } // Non-inline
}

// Note: it is only OK if var do not have a backing field.

// Apart from inlining individual accessors, you can also mark a property
// itself with the inline modifier. In this case, the compiler will inline
// both getter and setter (if the property is mutable):
class Human1(var name: String, var lastName: String) {
    inline var fullName
        get() = "$name $lastName" // Inline getter
        set(value) {  } // Non-inline
}

// Note that inlining is only supported for properties without the backing field.
// Also, similar to functions, you may not refer to private declarations if your
// property is public:
class Person1(private val firstName: String, private val familyName: String) {
    // inline var age = 0 // Error: property has a backing field

    // inline val fullName get() = "$firstName $familyName"
    // Error: firstName and familyName are private
}


fun main() {
    program1()
}
