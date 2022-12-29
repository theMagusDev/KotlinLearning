package practice

class Person (val name: String?) {

    fun appraiseNameLength(): String {
        if (this.name != null) {
            return when (this.name.length) {
                in 1..5 -> "OK length"
                in 6..10 -> "Pretty long"
                in 11..26 -> "Extremely long"
                else -> "Invalid"
            }
        }
        return "null"
    }
}

fun main() {
    val person1 = Person("Yuriy")
    val person2 = Person("Anastasia")
    val person3 = Person("Abdurahmangaji")
    println(person1.appraiseNameLength())
    println(person2.appraiseNameLength())
    println(person3.appraiseNameLength())
}