package kotlinInDepth.basicStatements.functions

fun main () {
    /* Varargs */

    fun printSorted(vararg items: Int) {
        // Ints -> IntArray with name 'items'
        // so the type of 'items' is IntArray
        items.sort()
        println(items.contentToString())
    }
    printSorted(6, 2, 10, 1) // [1, 2, 6, 10]

    val numbers = intArrayOf(6, 2, 10, 1)
    printSorted(*numbers) // [1, 2, 6, 10]
    // printSorted(numbers) // Type mismatch. Required: Int; Found: IntArray
    /*
    Note: spread ('*') creates an array copy, so changes to elements of items
    parameter do not affect values of the numbers elements:
     */
    val newNumbers = intArrayOf(6, 2, 10, 1)
    printSorted(*newNumbers) // [1, 2, 6, 10]
    println(newNumbers.contentToString()) // [6, 2, 10, 1]

    /*
    However, if array elements are themselves references,
    copying those references lead to sharing the data
    between the function and its caller:
     */
    fun change(vararg items: IntArray) {
        items[0][0] = 100
        // assigns 100 to the first array's element with index 0
    }
    val a = intArrayOf(1, 2, 3)
    val b = intArrayOf(4, 5, 6)
    val ab = arrayOf(a, b)
    change(*ab)
    println(a.contentToString()) // [100, 2, 3]
    println(b.contentToString()) // [4, 5, 6]

    // Note: Declaring more than one parameter as vararg is forbidden.

    printSorted(6, 1, *intArrayOf(3, 8, 5), 2) // [1, 2, 3, 5, 6, 8]

    // it’s considered a good style to place the vararg parameter
    // at the end of the parameter list.

    // vararg itself can’t be passed as a named
    // argument unless you’re using the spread operator:
    printSorted(items = *intArrayOf(1, 2, 3))
    // printSorted(items = 1, 2, 3) // Error

    /*
    Placing defaults before vararg will force first values of the
    vararg argument to be interpreted as values of preceding defaults unless
    you pass vararg in a named form which defeats the purpose of using
    vararg in the first place:
     */
    fun newPrintSorted1(prefix: String = "", vararg items: Int) {}
    // newPrintSorted(6, 2, 10, 1) // Error: 6 is taken as value of prefix
    // newPrintSorted(*intArrayOf(6, 2, 10, 1), prefix = "123") // Error
    newPrintSorted1(items = *intArrayOf(6, 2, 10, 1)) // Correct
    newPrintSorted1(items = intArrayOf(6, 2, 10, 1)) // Correct

    // Placing defaults after the vararg parameter, on the other hand,
    // will require you to use the named form for the defaults:
    fun newPrintSorted2(vararg items: Int, prefix: String = "") {}
    // newPrintSorted2(6, 2, 10, 1, "!") // Error: "!" is taken as part of vararg
    newPrintSorted2(6, 2, 10, 1, prefix = "!") // Correct

    // function with vararg parameter, all other things being equal,
    // is considered less specific than a function, that
    // have a fixed number of parameters of the same type:
    fun printSorted1(vararg items: Int) {} // #1
    fun printSorted1(a: Int, b: Int, c: Int) {} // #2
    printSorted1(1, 2, 3) // Choosing #2 between #1 and #2 as non-vararg function
    printSorted1(1, 2)  // Choosing 1 as the only acceptable candidate

}