package kotlinInDepth.basicStatements.packagesAndImports
/*
* Note, that there's no error in line: 'package packagesAndImports.part1',
* even though there is no part1 folder. (Unlike in Java)
*/

// In Kotlin the package hierarchy is a separate structure.
// It may coincide with a directory structure
// of the source file tree, but that’s not necessary.

// It is, however, recommended to keep
// the directory and package structure matched.

import kotlinInDepth.basicStatements.functions.giveFive
import kotlinInDepth.basicStatements.functions.*

fun main() {
    // call a function from a different package:
    kotlinInDepth.basicStatements.functions.giveFive() // using the full name
    giveFive() // thanks to the line #3
    circleArea(3.5) // thanks to the line #4
}

class Packages {
    fun abc(){}
}