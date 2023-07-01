package kotlinInDepth.collenctions.collection.collectiveConditions

// The all() function returns true if all collection elements
// satisfy a given predicate.
fun allFunction() {
    println(listOf(1, 2, 3, 4).all {it < 10}) // true
    println(listOf(1, 2, 3, 4).all {it % 2 == 0}) // false
    println(
        mapOf(1 to "one", 2 to "two", 3 to "three", 10 to "ten")
            .all { it.key < 10 && it.value.length <= 3}
    ) // false
    println(
        mapOf(1 to "one", 2 to "two", 3 to "three", 10 to "ten")
            .all { it.key <= 10 && it.value.length <= 5}
    ) // true
}

// The none() function tests the opposite condition.
// It returns true when there is no collection element
// satisfying a predicate.
fun noneFunction() {
    println(listOf(1, 2, 3, 4).none {it > 5}) // true
}

// any() function returns true when a predicate
// is satisfied by at least one collection element
fun anyFunction() {
    println(listOf(1, 2, 3, 4).any {it == 3}) // true
}

fun main() {
    allFunction()
    noneFunction()
    anyFunction()
}