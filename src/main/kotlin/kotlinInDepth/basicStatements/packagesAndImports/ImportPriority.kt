package kotlinInDepth.basicStatements.packagesAndImports

import kotlinInDepth.basicStatements.packagesAndImports.helperPackage.p1.abc
import kotlinInDepth.basicStatements.packagesAndImports.helperPackage.p2.*

fun main() {
    abc() // directive imports have a higher priority
}