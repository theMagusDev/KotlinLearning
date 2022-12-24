package packagesAndImports

import java.lang.Math // import classes
import functions.giveFive // import top-level functions
import kotlin.Int.Companion.MIN_VALUE // import static (companion objects') values

// Note: Unlike Java, Kotlin doesn’t have a separate construct,
// which imports type members similar to Java’s “import static”.

// All declarations in Kotlin are imported using
// the general import directive syntax.

/* 'as' keyword */
import packagesAndImports.helperPackage.p1.abc
import packagesAndImports.helperPackage.p2.abc

/*
fun useFuns() {
    abc() // Error: which method abc() to use ???
}
*/

import packagesAndImports.helperPackage.p1.abc as abc1
import packagesAndImports.helperPackage.p2.abc as abc2

fun useFuns() {
    abc1() // OK
    abc2() // OK
}