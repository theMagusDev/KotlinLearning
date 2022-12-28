package kotlinInDepth.conditionals

fun oldHexDigit(n: Int): Char {
    if (n in 0..9) return '0' + n
    else if (n in 10..15) return 'A' + n - 10
    else return '?'
}

fun newHexDigit1(n: Int): Char {
    when(n) /* using a subject expression */ {
        in 0..9 -> return '0' + n
        in 10..15 -> return 'A' + n - 10
        else -> return '?'
    }
}

fun newHexDigit2(n: Int) = when(n) {
    in 0..9 -> '0' + n
    in 10..15 -> 'A' + n - 10
    else -> '?'
}

// Unlike in Java, Kotlin's when allows you to check arbitrary conditions:
fun newHexDigit3(n: Int, nCopy: Int = n) = when /* No need in '(n)' here */ {
    n in 0..9 -> '0' + n
    nCopy in 10..15 -> 'A' + n - 10 // you can check arbitrary conditions, not only 'n'
    else -> '?'
}

fun abc(n: Int) {
    when(n) {
        1, 2, 3 -> {
            println(1..3)
            // break is put here in Java bytecode (if it compiles when into switch)
        }
        else -> println(">= 4")
    }
}

// when statements allow you to bind the subject expression
// to a variable using the following syntax:
fun readHexDigit() = when(val n = readln().toInt()) { // define n. You can not use var here instead of val
    in 0..9 -> '0' + n
    in 10..15 -> 'A' + n - 10
    else -> '?'
}


