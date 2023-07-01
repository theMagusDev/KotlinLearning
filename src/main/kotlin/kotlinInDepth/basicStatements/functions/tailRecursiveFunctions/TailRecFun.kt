package kotlinInDepth.basicStatements.functions.tailRecursiveFunctions

/*
A function that implements a binary
search in a sorted integer array
 */
tailrec fun binIndexOf (
    x: Int,
    array: IntArray,
    from: Int = 0,
    to: Int = array.size
): Int {
    if (from == to) return -1
    val midIndex = (from + to - 1) / 2
    // [1, 2, 3, 4, 5]. from = 0, to = 5, midIndex = (0 + 5 - 1) / 2 = 2
    val mid: Int = array[midIndex]
    // mid = 3
    return when {
        mid < x -> binIndexOf(x, array, midIndex + 1, to)
        // x in righter than mid, so set from to midIndex + 1
        mid > x -> binIndexOf(x, array, from, midIndex)
        // x in lefter than mid, so set 'to' to midIndex
        else -> midIndex
        // mid = x, so midIndex is the result
    }
}

/*
In Kotlin, you can tell a compiler to automatically
translate a tail-recursive function into non-recursive code
by adding the 'tailrec' modifier. As a result, you get the best
of both worlds: a concise recursive function with
no extra performance penalties. The preceding function,
in particular, would be equivalent to the code:
 */

fun compiledBinIndexOf(
    x: Int,
    array: IntArray,
    from: Int = 0,
    to: Int = array.size
): Int {
    var fromIndex = from
    var toIndex = to
    while (true) {
        if (fromIndex == toIndex) return -1
        val midIndex = (fromIndex + toIndex - 1) / 2
        val mid = array[midIndex]
        when {
            mid < x -> fromIndex = midIndex + 1
            mid > x -> toIndex = midIndex
            else -> return midIndex
        }
    }
}
/*
To be eligible for such a transformation, the function must not perform any
action after a recursive call. That’s the meaning behind tail-recursive. If this
requirement is not satisfied but the function is still marked as tailrec, the
compiler will issue a warning and the function will be compiled as a
recursive one. For example, the following summation function is not tailrecursive
because the sum(array, from + 1, to) call is followed by addition:
 */
tailrec fun sum(array: IntArray, from: Int = 0, to: Int =
    array.size): Int {
    // Warning: not a tail-recursive call
    return if (from < to) array[from] + sum(array, from +
            1, to) else 0
}

/*
The compiler will also report a warning if the function is marked as
tailrec but contains no recursive calls:
 */
tailrec fun sum(a: Int, b: Int): Int {
    return a + b // Warning: no tail-recursive calls
}

