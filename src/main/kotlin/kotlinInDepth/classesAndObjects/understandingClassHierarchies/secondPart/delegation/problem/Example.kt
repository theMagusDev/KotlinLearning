package kotlinInDepth.classesAndObjects.understandingClassHierarchies.secondPart.delegation.problem

interface PersonData {
    val name: String
    val age: Int
}

open class Person(
    override val name: String,
    override val age: Int
) : PersonData

data class Book(val title: String, val author: PersonData) {
    override fun toString() = "'$title' by ${author.name}"
}

fun program1() {
    val levTolstoy = Person(name = "Lev Tolstoy", age = 36)
    val warAndPeace = Book(title = "War and Peace", author = levTolstoy)
    println(warAndPeace) // 'War and Peace' by Lev Tolstoy
}

// Suppose that we want writers to have pen names allowing them
// to pose as another person.

class Alias(
    private val realIdentity: PersonData,
    private val newIdentity: PersonData
) : PersonData {
    override val name: String
        get() = newIdentity.name
    override val age: Int
        get() = newIdentity.age
}

// Now we can use this class to create person aliases:
fun program2() {
    val levTolstoy = Person(name = "Lev Tolstoy", age = 36)
    val tolstoyTheGreat = Alias(levTolstoy, Person("Tolstoy the Great", 30))
    val warAndPeace = Book(title = "War and Peace", author = tolstoyTheGreat)
    println(warAndPeace) // 'War and Peace' by Tolstoy the Great
}

// The problem of such an approach is the amount of boilerplate code you
// have to generate to delegate all necessary methods and properties to another
// object. See 'solution' package for more details.

fun main() {
    program1()
    program2()
}
