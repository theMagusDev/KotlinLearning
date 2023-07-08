package kotlinInDepth.classesAndObjects.understandingClassHierarchies.secondPart.sealedClass.thirdSolution

// In Kotlin, this problem can be overcome courtesy of sealed classes. Let’s
// change our class definition by adding the sealed modifier.

sealed class Result {
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

// When the class is marked as sealed, its inheritors may be declared in either
// its body as nested classes and objects, or as top-level classes in the same file.

// Note that the sealed class is also abstract, so you can’t create its instance
// directly. The idea is that any instance of a sealed class must be created
// through one of its subclasses.

// In fact, sealed class constructors are private by default, and declaring them
// with some other visibility modifier is considered a compile-time error

sealed class Test() {
    constructor(a: Int) : this() // OK
//  public constructor(a: Int, b: Int) : this() // Error: Constructor must be private or protected in sealed class
}

// Similar to enums, sealed classes support the exhaustive form of the when
// expression that allows us to avoid redundant else branches

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
    } // exhaustive when expression
    println(message)
}
