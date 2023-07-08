package kotlinInDepth.classesAndObjects.understandingClassHierarchies.secondPart.sealedClass.secondSolution

/*
In some cases, however, different variants may have their own attributes.
For example, a state of successful completion may be accompanied by a
produced result, while a state of error may carry some information about its
cause.
Similar to examples we’ve already discussed in this chapter such
concepts can be modeled with a class hierarchy where the root abstract
class expresses the concept in general and its subclasses serve as
representations of particular variants. Let’s refine our example and add
some members to Success and Error cases.
*/

abstract class Result {
    class Success(val value: Any) : Result() {
        fun showResult() {
            println(value)
        }
    }

    class Error(val message: String) : Result() {
        fun throwException() {
            throw Exception(message)
        }
    }
}

fun runComputation(): Result {
    try {
        val a = readlnOrNull()?.toInt()
            ?: return Result.Error(message = "Missing first argument")
        val b = readlnOrNull()?.toInt()
            ?: return Result.Error(message = "Missing second argument")
        return Result.Success(a + b)
    } catch (e: NumberFormatException) {
        return Result.Error(e.message ?: "Invalid input")
    }
}

fun main() {
    val message = when (val result = runComputation()) {
        is Result.Success -> "Completed successfully: ${result.value}"
        is Result.Error -> "Error: ${result.message}"
        else -> return
    }
    println(message)
}

/*
But this implementation is not flawless. It doesn't allow you to express the
fact that the set of Result variants is restricted to Success and Error. In
particular, nothing prevents some client code from adding a new subclass,
say:
 class MyStatus: Result()
It’s also the reason why we have to add the else clause to the when
expression.
 */
