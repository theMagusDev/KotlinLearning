package kotlinInDepth.specialCaseClasses.dataClass

// Kotlin provides a useful feature to declare classes with a
// primary goal of storing some data. This feature is called data classes.

// Consider, for example, the following class:
class ExamplePerson(val firstName: String, val surname: String, val age: Int)

// What if we want to compare its instances by equality?
// All is similar to Java.

fun program1() {
    val person1 = ExamplePerson("John", "Doe", 25)
    val person2 = ExamplePerson("John", "Doe", 25)
    val person3 = person1
    println(person1 == person2) // false, different identifiers.
    println(person1 == person3) // true, the same identifiers.
}

// If we need a custom equality for the out class, we usually implement it with
// the equals() method accompanied by the corresponding hashCode() method
// which allows you to use class instances as keys in collections like HashMap.
// For a certain variety of classes, called data classes, Kotlin can
// generate these methods automatically based on a list of class properties.

data class Person(
    val firstName: String,
    val surname: String,
    val age: Int
)

fun program2() {
    val person1 = Person("John", "Doe", 25)
    val person2 = Person("John", "Doe", 25)
    val person3 = person1
    println(person1 == person2) // true
    println(person1 == person3) // true
}

// equals() is automatically overridden to compare
// each property instead of identifiers.

// Note that comparisons of property values are based on
// their equals() method too.

data class ExampleMailbox(val address: String, val person: ExamplePerson)
data class Mailbox(val address: String, val person: Person)

fun program3() {
    val box1 = ExampleMailbox("Sovetskaya street, 275A, 36", ExamplePerson("John", "Doe", 25))
    val box2 = ExampleMailbox("Sovetskaya street, 275A, 36", ExamplePerson("John", "Doe", 25))
    println(box1 == box2) // false. Because is compares each value.
    // "Sovetskaya street, 275A, 36".equals("Sovetskaya street, 275A, 36") // true
    // ExamplePerson("John", "Doe", 25).equals(ExamplePerson("John", "Doe", 25)) // false, because equals() is not overridden
    // and just compares identifiers.

    val box3 = Mailbox("Sovetskaya street, 275A, 36", Person("John", "Doe", 25))
    val box4 = Mailbox("Sovetskaya street, 275A, 36", Person("John", "Doe", 25))
    println(box3 == box4) // true
}

// toString() is also overridden.

fun program4() {
    val person = Person("John", "Doe", 25)
    println(person) // Person(firstName=John, surname=Doe, age=25)
}

// Note that only properties declared as parameters of the primary constructor
// are used in the equality/hash code/String conversion. Any other properties
// do not affect the result:

data class Musician(val firstName: String, val surname: String) {
    var instrument = "No instrument"
}

fun program5() {
    val musician1 = Musician("John", "Doe").apply { var instrument = "Guitar" }
    val musician2 = Musician("John", "Doe").apply { var instrument = "Drums" }
    println(musician1 == musician2) // true
}

// Any data class implicitly provides the copy() function which allows you
// to create a copy of the current instance with a possible
// change of some properties.

fun Person.show() = println("$firstName $surname: $age")
fun program6() {
    val person = Person("John", "Doe", 25)
    person.show() // John Doe: 25
    person.copy().show() // John Doe: 25
    person.copy(surname = "Smith").show() // John Smith: 25
    person.copy(age = 30, firstName = "Jane").show() // Jane Doe: 30
}

// The ability to easily copy an instance encourages the usage of immutable
// data classes. Although the var properties are allowed, it’s often reasonable
// to design data classes as immutable.

// The Kotlin standard library includes two general-purpose data classes
// which can be used to hold a pair or a triplet of values:

fun program7() {
    val pair = Pair(1, "two")
    println(pair.first) // 2
    println(pair.second) // two

    val triple = Triple("one", 2, false)
    println(triple.first) // one
    println(triple.second - 1) // 1
    println(!triple.third) // true

    // Pairs can also be constructed using the
    // infix operation 'to', returning Pair<A, B>:

    val anotherPair = 1 to "two"
    println(pair.first) // 1
    println(pair.second) // two
}

fun main() {
    program1()
    program2()
    program3()
    program4()
    program5()
    program6()
}