package kotlinInDepth.collenctions.collection.collectionProcessing.ordering

fun example() {
    val numbers = IntArray(7) {it+1}.apply { shuffle() } // [6, 5, 1, 3, 7, 2, 4]

    // sorted(() and sortedDescending(). Iterable<T> -> List<T>
    println(numbers.sorted()) // [1, 2, 3, 4, 5, 6, 7]
    println(numbers.sortedDescending()) // [7, 6, 5, 4, 3, 2, 1]

    // sortedArray() and sortedArrayDescending(). Array<T> -> Array<T>
    println(numbers.sortedArray().contentToString()) // [1, 2, 3, 4, 5, 6, 7]
}

class Person(val name: String, val surname: String, var age: Int) {
    override fun toString() = "$name $surname: $age"
}

val Person.fullname
    get() = "$name $surname"
val FULL_NAME_COMPARATOR = Comparator<Person> { person1, person2 ->
    person1.fullname.compareTo(person2.fullname)
}

fun sortObjects() {
    val persons = listOf(
        Person("Jake", "Hudson", 25),
        Person("Silver", "Watts", 30),
        Person("Dane", "Hall", 19),
        Person("Yaroslav", "Ortiz", 28)
    )

    // sortedWith() takes a comparator
    // sortedBy() takes a function which converts collection elements to comparables

    println(persons.sortedWith(FULL_NAME_COMPARATOR))
    // [Dane Hall: 19, Jake Hudson: 25, Silver Watts: 30, Yaroslav Ortiz: 28]
    println(persons.sortedBy { person -> person.fullname })
    // [Dane Hall: 19, Jake Hudson: 25, Silver Watts: 30, Yaroslav Ortiz: 28]
    println(persons.sortedBy { it.age })
    // [Dane Hall: 19, Jake Hudson: 25, Yaroslav Ortiz: 28, Silver Watts: 30]
}

fun sortAndModify() {
    // sort(). Array<T> -> Array<T>
    val intArray = intArrayOf(4, 0, 8, 9, 2).apply { sort() }
    println(intArray.contentToString()) // [0, 2, 4, 8, 9]

    val doubleArray = doubleArrayOf(2.7, 1.3, 5.6, 0.7, 5.1).apply { reverse() } // reverse the order of elements
    println(doubleArray.contentToString()) // [5.1, 0.7, 5.6, 1.3, 2.7]

    val sequence = generateSequence(1) { if (it > 9) null else it*it }
    println(sequence.any())
}

fun main() {
    example()
    sortObjects()
    sortAndModify()
}