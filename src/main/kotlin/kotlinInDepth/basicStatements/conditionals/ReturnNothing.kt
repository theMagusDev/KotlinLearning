package kotlinInDepth.basicStatements.conditionals

/*
* Sometimes, it can be helpful to use return in an if expression. The return
statement can be used as an expression of a special type Nothing which
denotes a non-existing value. Basically, if some expression has the Nothing
type, it indicates some break in the sequential control-flow of the program
since such an expression never reaches any definite value. In case of
return, it means termination of the enclosing function. One useful aspect
of the Nothing type is that it’s considered a subtype of every Kotlin type
and thus its expressions may be used in any context where an expression is
expected. Suppose we’re given a qualified package name and want to know
how it would look like if its simple name was changed:
 */

fun renamePackage(fullName: String, newName: String): String {
    var st: Int = -1
    for (i in fullName.indices) {
        if (fullName[i] == '.')
            st = i
    }
    val prefix = if (st >= 0) {
        fullName.substring(0, st)
    } else {
        return newName
    }

    return "$prefix.$newName"
}

fun main() {
    println(renamePackage("foo.bar.old", "new")) // foo.bar.new
}

/*
* Note that the value of newName in 'return newName' is not the value of the
return expression, but rather a resulting value of the enclosing function. The
return expression itself has no value just like any expression of the type
Nothing. Keep in mind the difference between Unit and Nothing: as
opposed to Nothing, Unit has a single instance which is generally used to
denote the absence of any useful value, rather than an absence of any value
at all.
 */