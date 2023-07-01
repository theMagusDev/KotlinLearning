package kotlinInDepth.collenctions.collection.createACollection

// create collections by an ordinary constructor call just like in Java:

fun program1() {
    val list = ArrayList<String>()
    list.add("Hello")
    list.add("What's up")
    list.add("Bye")
    println(list) // [Hello, What's up, Bye]

    val set = HashSet<Int>()
    set.add(12)
    set.add(31)
    set.add(12)
    println(set) // [12, 31]

    val map = HashMap<Int, String>()
    map.put(1, "one")
    map[2] = "two"
    map.putIfAbsent(2, "three")
    println(map) // {1=one, 2=two}
}

fun program2() {
    // list
    val immutableList = listOf("Hello", "Bye") // immutable list
    val mutableList = mutableListOf("Hello", "Bye") // mutable list
    val listWithoutNulls = listOfNotNull("Hello", null, "Bye") // ["Hello", "Bye"]

    // set
    val immutableSet = setOf("Hello", "Bye") // immutable set
    val mutableSet = mutableSetOf("Hello", "Bye") // mutable set

    // arrayList
    val arrayList = arrayListOf("Hello", "Bye")

    // map
    // mapOf(); mutableMapOf() creates a LinkedHashMap
    // hashMapOf() / linkedMapOf() / sortedMapOf()

    // hashSet
    // hashSetOf(); linkedSetOf(); sortedSetOf()
    // This creates a new instance
    // of HashSet/LinkedHashSet/TreeSet, respectively
}

fun program3() {
    // Create lists with custom initialization
    val squares = List(size = 5) { it * it } // [0, 1, 4, 9, 16]
}

fun program4() {
    // You can convert one type of collection to another

    println(listOf(1, 2, 3, 4, 5, 5, 5).toSet()) // [1, 2, 3, 4, 5]
    println(mapOf(1 to "one", 2 to "two", 3 to "three").toList()) // [(1, one), (2, two), (3, three)]

}

fun main() {
    program1()
    program2()
    program3()
    program4()
}