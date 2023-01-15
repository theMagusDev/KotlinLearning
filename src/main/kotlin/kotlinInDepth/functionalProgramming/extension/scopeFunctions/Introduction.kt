package kotlinInDepth.functionalProgramming.extension.scopeFunctions

// The Kotlin standard library includes a set of functions which allow you to
// introduce a temporary scope where you can refer to the value of given
// context expression. Sometimes, this can be helpful to avoid an explicit
// introduction of local variables in containing the scope to hold
// an expression value and simplify the code. These functions are
// usually called scope functions.

/*
The basic effect is a simple execution of a lambda you will provide as
an argument. The difference comes from the combination of the
following aspects:

* Whether the context expression is passed as a receiver or an ordinary argument.
* Whether the lambda is an extension or not.
* Whether the function returns the value of lambda or the value of the context expression.
 */

// Overall there are five standard scope functions:
// run, let, with, apply, also.

