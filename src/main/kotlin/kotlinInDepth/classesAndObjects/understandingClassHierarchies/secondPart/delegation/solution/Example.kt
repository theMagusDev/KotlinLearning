package kotlinInDepth.classesAndObjects.understandingClassHierarchies.secondPart.delegation.solution

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

// Luckily for us Kotlin has a built-in support for delegates. All you
// have to do is specify a delegate instance after the by keyword following a
// superinterface name

class Alias(
    private val realIdentity: PersonData,
    private val newIdentity: PersonData
) : PersonData by newIdentity { // now newIdentity gives implementation of all necessary functions
    // suppose, we want to give a custom implementation of some method
    override val age: Int
        get() = realIdentity.age // get real age
}

fun program1() {
    val levTolstoy = Person(name = "Lev Tolstoy", age = 36)
    val tolstoyTheGreat = Alias(levTolstoy, Person("Tolstoy the Great", 30))
    val warAndPeace = Book(title = "War and Peace", author = tolstoyTheGreat)
    println(warAndPeace) // 'War and Peace' by Tolstoy the Great
}

/* We can also drop 'val' on newIdentity making it a simple parameter:
 class Alias(
     private val realIdentity: PersonData,
     newIdentity: PersonData
 ) : PersonData by newIdentity
*/

// Combining the delegation with object expressions can be useful to create an
// implementation with slightly different behavior than the original object
fun PersonData.aliased(newIdentity: PersonData) =
    object : PersonData by newIdentity {
        override val age: Int
            get() = this@aliased.age // means this Person, but not this object
    }

fun program2() {
    val levTolstoy = Person(name = "Lev Tolstoy", age = 36)
    val tolstoyTheGreat = levTolstoy.aliased(Person("Tolstoy the Great", 30))
    val warAndPeace = Book(title = "War and Peace", author = tolstoyTheGreat)
    println(warAndPeace) // 'War and Peace' by Tolstoy the Great
}

/* Note that a class may only delegate an implementation of interface members.
 class AliasTest(
     private val realIdentity: PersonData,
     private val newIdentity: PersonData
 ) : Person by newIdentity // Error: only interfaces can be delegated to
*/

fun main() {
    program1()
    program2()
}