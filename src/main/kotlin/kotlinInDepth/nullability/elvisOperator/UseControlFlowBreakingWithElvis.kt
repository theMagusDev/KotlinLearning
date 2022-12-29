package kotlinInDepth.nullability.elvisOperator

class Info(val name: String, val surname: String?)

class Person(val info: Info?) {
    fun describe(): String {
        val actualInfo = info ?: return "Unknown"
        return "${actualInfo.name} ${actualInfo.surname}"
    }
}

fun main() {
    println(Person(Info("John", "Doe")).describe()) // John Doe
    println(Person(null).describe()) // Unknown
}
