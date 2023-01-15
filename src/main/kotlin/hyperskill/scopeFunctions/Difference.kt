package hyperskill.scopeFunctions

/* What is the difference: context object */
/*
In terms of scope functions, there are five practical differences
between the lambda receiver 'this' and the lambda argument 'it'.

1) 'this' refers directly to the context object, while 'it' can refer
to separate members of the object on which we invoke a scope function.

2) In both cases, we refer to some object with which we will work,
but with 'it' we can pass several parameters, while
with 'this' we're dealing with one function parameter.

3) We can omit 'this' in a function and access the arguments or
object methods directly, but in the case of 'it', we have to call
arguments and parameters with the 'it' keyword.

4) With 'it' we can rename our parameters and
use a more appropriate name inside the function.

5) If you have only one function in the body and 'it' as a parameter,
you can replace that with the :: reference to the function.

run(), with(), and apply() work with 'this', while let() and also() work with 'it'.
 */

data class NewMusician(var name: String, var instrument: String = "Guitar", var band: String = "Radiohead")

fun main() {
    NewMusician("Jonny Greenwood").apply {
        instrument = "harmonica" // here we can use this.instrument
        band = "Pavement"
    }

    NewMusician("Jonny Greenwood").also {
        it.instrument = "harmonica"
    }

    NewMusician("Dave Glowl", "Drums", "Nirvana").let { (musicianName, instrument, newBand) ->
        return@let musicianName.length + instrument.length + newBand.count { it == 'a' }
    }
    // With it, we can pass several parameters and use them as separate parameter members. We can also rename these parameters
}

/* Kotlin coding conventions:

* Executing a lambda on non-null objects: let
* Introducing an expression as a variable in local scope: let
* Object configuration: apply
* Object configuration and computing the result: run
* Running statements where an expression is required: non-extension run
* Additional effects: also
* Grouping function calls on an object: with
 */
