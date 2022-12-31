package kotlinInDepth.nullability.safeCast

fun main() {
    print("Please, enter the number: ")
    val a = readln()
    val aInt: Int? = a as? Int // null, because String can not be cast to Int
    val bAny: Any? = a as? Any // OK, because String can be cast to Any
    println(aInt) // null
    println(bAny) // 5
}