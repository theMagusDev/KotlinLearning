package practice.myOwnHashMap

fun main() {
    val awesomeHashMap = AwesomeHashMap<String, String>()
    awesomeHashMap.put(key = "Dev", value = "Yuriy")
    awesomeHashMap.put(key = "Greeting", value = "Hello, world!")

    println("Key 2 = ${awesomeHashMap.get("Dev")}")
    println("Key 1 = ${awesomeHashMap.get("Greeting")}")
    println("Key 3 = ${awesomeHashMap.get("Result")}")

    println("Hash 1 = ${1.hashCode()}")
    println("Hash 2 = ${2.hashCode()}")
    println("Hash 3 = ${2.hashCode()}")
}