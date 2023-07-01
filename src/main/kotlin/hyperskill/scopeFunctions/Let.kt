package hyperskill.scopeFunctions

// let() //

// When we use let, it sounds like:
// "Do some work with a certain object"

/* Features:

* Is an extension function.
* Context object is available as 'it'.
* Returns the result of a lambda.

Use when:

    1) First, let can be used to invoke one or more functions on
    results of call chains. For example, the following code prints the
    results of two operations on a collection:
 */

// Old way:
fun program7() {
    fun printMagicList(list: List<Int>) = print("*** ${list} ***\n")

    val numbers = mutableListOf("one", "two", "three", "four", "five")
    val resultList = numbers.map { it.length }.filter { it > 3 }
    println(resultList)
    printMagicList(resultList)
    // and more functions calls if needed
}

// New way:
fun program8() {
    fun printMagicList(list: List<Int>) = print("*** ${list} ***\n")
    val numbers = mutableListOf("one", "two", "three", "four", "five")

    numbers.map { it.length }.filter { it > 3 }.let {
        println(it)
        printMagicList(it)
        // and more functions calls if needed
        // ...
    }
}

// Remember: If the code block contains a single function with it as an argument,
// you can use the method reference (::) instead of the lambda:

fun program9() {
    fun printMagicList(list: List<Int>) = print("*** ${list} ***\n")
    val numbers = mutableListOf("one", "two", "three", "four", "five")

    numbers.map { it.length }.filter { it > 3 }.let(::printMagicList)
}

/*
    2) Second, when we want to do something with the safety call operator '?' and non-null objects
*/

fun program10() {
    fun processNonNullString(s: String) = s.filter { it.isLowerCase() }
    val str: String? = "Jonny Greenwood"

    // processNonNullString(str) // compilation error: str can be null
    // println(str.length) // compilation error: str can be null

    val length = str?.let {
        println("let() is called on $it")
        processNonNullString(it)      // OK: 'it' is not null inside '?.let { }'
        it.length // the last lambda expression is the return value
    } ?: -1 // '-1' if 'str' is null
    println(length)
}

/*
    3) Third, we use let when we want to enter local variables with a limited scope.
 */
fun program11() {
    val musicians = mutableListOf("Thom York", "Jonny Greenwood", "Colin Greenwood")
    val modifiedFirstItem = musicians.first().let { firstItem ->
        println("The first item of the list is $firstItem")
        if (firstItem.length > 5) firstItem else "!${firstItem}!"
    }
    // if first musician's name's length is greater than 5, return it as is,
    // else surround it with ![name&surname]!
}

/* main() function */

fun main() {
    program7()
    program8()
    program9()
    program10()
    program11()
}


