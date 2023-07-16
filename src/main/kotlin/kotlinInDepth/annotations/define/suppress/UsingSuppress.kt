package kotlinInDepth.annotations.define.suppress

/*
When writing code, you've probably already seen IDE warnings about
code style – for example, about using magic numbers. Is it annoying?
It is, so let's learn our first annotation – @Suppress – which
will suppress such warnings.

@Suppress("name of warning") is an annotation that
tells the compiler to suppress a specific warning:
 */

fun program1() {
    var number = 5 // Warning: Variable is never modified, so it can be declared using 'val'
    println(number)
}

fun program2() {
    @Suppress("CanBeVal")
    var number = 5 // OK, no warnings
    println(number)
}

fun main() {
    program1()
    program2()
}
