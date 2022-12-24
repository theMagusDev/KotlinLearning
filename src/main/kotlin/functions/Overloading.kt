package functions

/* Algorithm:
1. Collect all functions which can be called with given arguments
according to the parameter count and types.
2. Remove all less specific functions. A function is less specific if all its
parameter types are supertypes of the corresponding parameters of
some other function in the candidate list. This step is repeated until no
less specific functions remain.
3. If the candidate list is reduced to a single function, it’s considered the
call target, otherwise the compiler reports an error.
 */

fun main() {
    fun mul(a: Int, b: Int) = "mul #1" // #1
    fun mul(a: Int, b: Int, c: Int) = "mul #2" // #2
    fun mul(s: String, n: Int) = "mul #3" // #3
    fun mul(o: Any, n: Int) = "mul #4" // #4

    mul(1, 2) // Choosing 1 between 1 and 4 since Int is a subtype of Any
    // mul(1, 2L) // Error: no overload accepts (Int, Long)
    mul(1L, 2) // Choosing 4 as it’s the only acceptable overload
    mul("0", 3) // Choosing 3 between 3 and 4 since String is a subtype of Any
    mul("0" as Any, 3) // Choosing 4 as it’s the only acceptable overload

    // Default values
    fun readIntLine() = readLine()!!.toInt()
    fun readIntLine(radix: Int) = readLine()!!.toInt(radix)

    // Can be written as follows:
    fun newReadIntLine(radix: Int = 10) = readln().toInt(radix = radix)
    val decimalInt = newReadIntLine()
    val decimalInt2 = newReadIntLine(10)
    val hexInt = newReadIntLine(16)

    /*
    Note that if some non-default parameters come after default ones, the only
    way to call such functions with default parameter(s) omitted is to use
    named arguments:
     */
    fun restrictToRange(
        from: Int = Int.MIN_VALUE,
        to: Int = Int.MAX_VALUE,
        what: Int
    ): Int {
        return Math.max(from, Math.min(to, what))
    }
    println(restrictToRange(10, what = 1))
    /*
    It is, however, considered a good style to put parameters
    with default values at the end of the parameter list:
    fun restrictToRange(
        what: Int,
        from: Int = Int.MIN_VALUE,
        to: Int = Int.MAX_VALUE
    ): Int {
        return Math.max(from, Math.min(to, what))
    }
     */
}

class OverloadingPart2 {

    fun main() {
        fun mul(a: Int, b: Int = 1) = a*b // #1
        fun mul(a: Int, b: Long = 1L) = a*b // #2
        fun mul(a: Int, b: Int, c: Int = 1) = a*b*c // #3

        // mul(10) // Error: can’t choose between #1 and #2
        mul(10, 20) // Choosing #1 between #1 and #3 as having fewer parameters
        mul(10, 20, 30) // Choosing #3 as the only acceptable candidate

        fun mul(a: Number, b: Int = 1) = a as Int * b // 4

        mul(10, 20) // Choosing #1 between #1, #3 and #4
        // as the #4 one will be considered less specific
        // due to Number being a supertype of Int

    }
}