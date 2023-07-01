package kotlinInDepth.functionalProgramming.nonLocalControlFlow

// Non-local return: A return statement which can terminate enclosing
// function from inside a lambda that is passed as its argument.

// Using higher-order functions raises some issues with instructions
// that break the normal control flow such as the return statement.

fun forEach(array: IntArray, action: (Int) -> Unit) {
    for (n in array) action(n)
}
fun program1() {
    forEach(intArrayOf(1, 2, 3, 4)) {
        // if (it < 2 || it > 3) return
        // Error, return is referring to the program1() fun from lambda
        println(it)
    }
}

// The intention was to return from lambda before printing a number if it
// doesn't fit into a range. However, this code won’t compile. This happens
// because a return statement by default is related to the nearest
// enclosing function defined with fun, get or set keywords. So in our example,
// we’re trying to return from the main() function instead. Such a statement,
// also known as non-local return, is forbidden because on JVM, there is no
// efficient way that would allow a lambda to force the return of
// its enclosing function.

// One way to solve the problem is to add the label to our lambda:
fun program2() {
    forEach(intArrayOf(1, 2, 3, 4)) lambda@ {
        if (it < 2 || it > 3) return@lambda
        println(it)
    }
}

// Another way is to use the anonymous function instead:
fun program3() {
    forEach(intArrayOf(1, 2, 3, 4), fun(it: Int) {
        if (it < 2 || it > 3) return // OK, referring to the anonymous fun
        println(it)
    })
}

// If we do want to return from a lambda itself, we need to qualify the return
// statement with a context name similar to the labelled break and continue.

val action: (Int) -> Unit = myFun@ {
    if (it < 2 || it > 3) return@myFun
    println(it)
}

// When lambda is passed as an argument to a higher-order function, however,
// it’s possible to use that function’s name as a context without introducing
// an explicit label.
fun program4() {
    forEach(intArrayOf(1, 2, 3, 4)) {
        if (it < 2 || it > 3) return@forEach
        println(it)
    }
}

// When lambda is inlined, we can use return statements to return from
// the enclosing function. This is possible because the lambda body
// is substituted into the call site together with a body of corresponding
// higher-order function, so the return statement would be treated as if
// it was placed directly in the body of main():
inline fun inlinedForEach(array: IntArray, action: (Int) -> Unit) {
    for (element in array) action(element)
}
fun program5() {
    inlinedForEach(intArrayOf(1, 2, 3, 4)) {
        if (it < 2 || it > 3) return // referring to program5()
        println(it)
    }
}
/* Java bytecode (simplified):

public static final void inlinedForEach(int[] array, Function1 action) {

    for(int i = 0; i < array.length; ++i) {
        int element = array[i];
        action.invoke(element);
    }
}

public static final void program5() {
    int[] array = new int[]{1, 2, 3, 4};

    for(int i = 0; i < array.length; ++i) {
        int element = array[i];

        // The following code is placed instead of action.invoke(element):

        if (element < 2 || element > 3) {
            return; // referring to the program5() function
        }
        System.out.println(element);
    }
}

 */

// There is a special case with calling inlinable lambda (our 'action' parameter)
// not directly in the body of a function it’s passed to, but in a separate
// execution context like a local function or a method of local class.
// Even though such lambdas are inlined, they are not able to force
// the return of the caller function since even after inlining, they
// would occupy different frames of the execution stack. For reasons
// such usages of functional parameters are forbidden by default.
private inline fun incorrectPrivateInlinedForEach(array: IntArray, action: (Int) -> Unit) =
    object {
        fun run() {
            for (element in array) {
                // action(element)
                // Error: Can't inline 'action' here:
                // it may contain non-local returns.

                // It's because action() lambda can contain non-local
                // return, which will referer to local function run()
            }
        }
    }

// To allow them, we need to mark a functional parameter with a crossinline
// modifier which leaves the functional value inlined but forbids
// using non-local returns inside a corresponding lambda:

private inline fun correctPrivateInlinedForEach(
    array: IntArray, crossinline action: (Int) -> Unit
) = object {

    fun run() {
        for (element in array) {
            action(element)
        }
    }
}
fun program6() {
    correctPrivateInlinedForEach(intArrayOf(1, 2, 3, 4)) {
        // if (it < 2 || it > 3) return // Error: 'return' is not allowed here
        println(it)
    }
    correctPrivateInlinedForEach(intArrayOf(1, 2, 3, 4)) {
        if (it < 2 || it > 3) return@correctPrivateInlinedForEach // OK
        println(it)
    }
}

// Non-local control flow issues may also arise when using break and continue
// statements since they can target a loop enclosing the lambda. Currently,
// they are not supported even if the lambda in question is inlined, although
// such support may be added in a future language version.
fun program7() {
    while (true) {
        forEach(intArrayOf(1, 2, 3, 4)) {
            // if (it < 2 || it > 3) break
            // Error: 'break' or 'continue'
            // jumps across a function or a class boundary
            // (means break is referring to the outer while loop)
            println(it)
        }
    }
}

/* Main function */
fun main() {
    program1()
    program2()
    program3()
    program4()
    program5()
    program6()
    program7()
}