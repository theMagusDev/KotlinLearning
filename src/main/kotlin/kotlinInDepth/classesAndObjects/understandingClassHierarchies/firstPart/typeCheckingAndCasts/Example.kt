package kotlinInDepth.classesAndObjects.understandingClassHierarchies.firstPart.typeCheckingAndCasts

fun isFunction() {
    // Example:
    println(String is Any) // true

    // For null values:
    println(null is Int) // false
    println(null is String?) // true

    // use '!' for inverted operation:
    println(Any() !is String) // true

    // The following check produced a compilation error since it’s meaningless
    // to test an Int value against String, compiler knows it
//  println(12 is String) // Error: Incompatible types: String and Int
}

fun smartCast() {
    // Practical example:
    val objects = ArrayList<Any>()
    objects.add("1")
    objects.add(2)
    objects.add("3")
    objects.add(4)
    objects.add("Hello")

    fun calculateSum(objects: ArrayList<Any>): Int {
        var summ = 0
        for (obj in objects) {
            when (obj) {
                is Int -> summ += obj // Smart cast to kotlin.Int
                is String -> summ += obj.toIntOrNull() ?: 0 // Smart cast to kotlin.String
            }
        }
        return summ
    }

    println(calculateSum(objects)) // 10
}

fun main() {
    isFunction()
    smartCast()
}