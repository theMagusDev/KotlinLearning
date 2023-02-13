package kotlinInDepth.specialCaseClasses.dataClass.destructuringDeclarations

import kotlin.random.Random
data class Person (
    val name: String,
    val surname: String,
    val age: Int
)
fun newPerson() = Person(
    readln(),
    readln(),
    Random.nextInt(until = 100)
)

// old way
fun oldCheckUnderAged() {
    val person = newPerson()
    val name = person.name
    val surname = person.surname
    val age = person.age

    if (age < 18) {
        println("$name $surname is under-age")
    }
}

// Since Person is a data class, we will use a much
// more concise syntax to define the corresponding local variables.

// new way
fun checkUnderAged() {
    val person = newPerson()
    val (name, surname, age) = person

    if (age < 18) {
        println("$name $surname is under-age")
    }
}

// This is a destructuring declaration which generalizes a local variable
// syntax by allowing you to use a parentheses-enclosed list of identifiers
// instead of a single variable name.

// Note that properties are mapped to the variables according to their position
// in the data class constructor rather than their name. So while the code:
fun example1() {
    val (name, surname, age) = Person("John", "Doe", 25)
    println("$name $surname: $age")
}
// produces the expected result “John Doe: 25”, the following lines:
fun example2() {
    val (surname, name, age) = Person("John", "Doe", 25)
    println("$name $surname: $age")
}
// will give you "Doe John: 25".


// How it works under a hood? See UnderAHood.KT for more info.


// A destructuring declaration may include fewer components than
// there are properties in a data class.
fun example3() {
    val (name, surname) = Person("John", "Doe", 25)
    println("$name $surname") // John Doe
    val (name2) = Person("John", "Doe", 25)
    println(name2) // John
}

// Replace unused components with the '_'
// symbol similar to unused parameters of a lambda expression:
fun example4() {
    val (_, familyName) = Person("John", "Doe", 25)
    println(familyName) // Doe
}

// Destructuring can also be used in the for loops:
fun example5() {
    val pairs = arrayOf(1 to "one", 2 to "two", 3 to "three")
    // Remember: 'to' creates a tuple of type Pair.
    for ((number, name) in pairs) {
        println("$number: $name")
    }
    // 1: one
    // 2: two
    // 3: three
}

// Since Kotlin 1.1, it’s possible to destructure a lambda parameter:
fun combine(
    person1: Person,
    person2: Person,
    folder: ((String, Person) -> String)
): String {
    return folder(folder("", person1), person2)
}
fun program1() {
    val person1 = Person("John", "Doe", 25)
    val person2 = Person("Jane", "Doe", 26)

    // Without destructuring:
    println(combine(person1, person2) { text, person -> "$text ${person.name}" })

    // With destructuring:
    println(combine(person1, person2) { text, (name) -> "$text $name" })
    println(combine(person1, person2) { text, (_, surname) -> "$text $surname" })

    // Note that unlike the ordinary lambda parameter list,
    // destructured parameters are enclosed in parentheses.
}

// Since destructuring declarations are currently only supported for
// local variables, they can’t be declared in a class body or at
// the top level in a file:

// val (name, surname) = Person("John", "Doe", 25) // Error

// Also destructuring declarations can’t be nested.

