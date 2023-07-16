package kotlinInDepth.annotations.define.deprecated.features

/*
Unlike Java, @Deprecated in Kotlin provides additional features. First, you
can specify a string with a replacement expression. In this case, deprecated
usage can automatically be changed into the desired form by using the
corresponding quick-fix from the Alt + Enter menu.
Note that ReplaceWith is an annotation too.

// See 'Deprecated2.jpg'

Another feature is an ability to choose the severity of deprecation which is
represented by the DeprecationLevel enum:
 1) WARNING: Usages of deprecated declarations are reported as warnings;
this is the default behavior
 2) ERROR: Usages of deprecated declarations are reported as compilation
errors.
 3) HIDDEN: Deprecated declaration can’t be accessed at all.

// See 'Deprecated3.jpg'
 */

@Deprecated(
    message = "Use readInt() instead",  // Message
    replaceWith = ReplaceWith("readInt()"), // Replacement expression
    level = DeprecationLevel.ERROR
)
fun readNum() = readln().toInt()

fun readInt(radix: Int = 10) = readln().toInt(radix)

fun main() {
//  val a = readNum() // Compilation error
//  val b = readNum() // Compilation error
//  println(a + b)
}

