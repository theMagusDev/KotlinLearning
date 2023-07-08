package kotlinInDepth.classesAndObjects.understandingClassHierarchies.secondPart.sealedClass.example

// file #4

// Thanks to the data class inheritance, it’s possible to
// use data classes as parts of sealed class hierarchy.
sealed class Expression
data class Constant(val num: Int): Expression()
data class Negative(val operand: Expression): Expression()
data class Plus(val operand1: Expression, val operand2: Expression): Expression()
data class Multiply(val operand1: Expression, val operand2: Expression): Expression()
fun Expression.evaluate(): Int = when (this) {
    is Constant -> num
    is Negative -> -1 * operand.evaluate()
    is Plus -> operand1.evaluate() + operand2.evaluate()
    is Multiply -> operand1.evaluate() * operand2.evaluate()
}
fun main() {
    // (1 + 2) * 3
    val expression = Multiply(Plus(Constant(1), Constant(2)), Constant(3))
    println(expression) // Multiply(operand1=Plus(operand1=Constant(num=1), operand2=Constant(num=2)), operand2=Constant(num=3))
    println(expression.evaluate()) // 9

    // 2 * 3
    val expression2 = expression.copy(operand1 = Constant(2))
    println(expression2) // Multiply(operand1=Constant(num=2), operand2=Constant(num=3))
    println(expression2.evaluate()) // 6
}



