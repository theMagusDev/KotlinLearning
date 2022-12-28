package kotlinInDepth.packagesAndImports

import kotlinInDepth.packagesAndImports.helperPackage.p1.abc
import kotlinInDepth.packagesAndImports.helperPackage.p2.*

fun main() {
    abc() // directive imports have a higher priority
}