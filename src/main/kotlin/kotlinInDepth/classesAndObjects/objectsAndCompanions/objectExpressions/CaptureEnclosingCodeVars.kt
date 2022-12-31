package kotlinInDepth.objectsAndCompanions.objectExpressions

// Similar to local functions and classes, object expressions can capture
//variables from the enclosing code. Mutable captured variables can be
//modified in the object’s body. In this case, a compiler creates necessary
//wrappers to share the data similar to the local classes:

fun main() {
    var x = 1
    /* Java bytecode:
    final Ref.IntRef x = new Ref.IntRef();
    x.element = 1;
     */

    val o = object {
        fun change() {
            x = 2
            // Java bytecode: x.element = 2;
        }
    }

    o.change()
    println(x) // 2
}
