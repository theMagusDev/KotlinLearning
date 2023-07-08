package kotlinInDepth.classesAndObjects.understandingClassHierarchies.secondPart.sealedClass.firstSolution

// Sometimes, the concepts we want to represent in a program may come in a
// fixed set of variants. In Chapter 6, we introduced an idea of the enum class
// which solves this problem
enum class Result {
    SUCCESS,
    ERROR
}
fun runComputation(): Result {
    try {
        val a = readlnOrNull()?.toInt() ?: return Result.ERROR
        val b = readlnOrNull()?.toInt() ?: return Result.ERROR
        println("Sum: ${a + b}")
        return Result.SUCCESS
    } catch (e: NumberFormatException) {
        return Result.ERROR
    }
}
fun main() {
    val message = when (runComputation()) {
        Result.SUCCESS -> "Completed successfully"
        Result.ERROR -> "Error!"
    }
    println(message)
}

