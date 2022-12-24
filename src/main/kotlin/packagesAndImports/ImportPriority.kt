package packagesAndImports

import packagesAndImports.helperPackage.p1.abc
import packagesAndImports.helperPackage.p2.*

fun main() {
    abc() // directive imports have a higher priority
}