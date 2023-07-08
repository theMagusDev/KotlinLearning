package kotlinInDepth.classesAndObjects.understandingClassHierarchies.firstPart.subclass

// Sometimes, an overridden version of a function or property needs to access
// its original version to reuse its code. In this case, you can prefix your
// member reference with the super keyword.

open class Linux {
    open fun start() {
        println("Linux OS is loading...")
    }
}
open class Android : Linux() {
    override fun start() {
        super.start()
        println("Android OS is loading...")
    }
}
class MIUI : Android() {
    override fun start() {
        super.start()
        println("MIUI is loading...")
    }
}
fun main() {
    val computerOS = Linux()
    val samsungOS = Android()
    val xiaomiOS = MIUI()

    computerOS.start()
    // Linux OS is loading...

    samsungOS.start()
    // Linux OS is loading...
    // Android OS is loading...

    xiaomiOS.start()
    // Linux OS is loading...
    // Android OS is loading...
    // MIUI is loading...
}