package kotlinInDepth.classesAndObjects.workingWithClassesAndObjects.classes.nested

/*
* In Kotlin, all nested classes are equivalent
* with public static class in Java.
* Note: Static nested classes object is not bound to outer classes object.
 */

class Person (private val passport: Passport, val age: Int) {

    class Passport(val name: String, val surname: String) {
    // equal to Java's: public static class
        fun isNameCorrect(person: Person) = name == person.passport.name // can access private elements of outer class
        // fun isNameCorrect() = name == this@Person.passport.name // But can not access outer class' instance's components
        // because Passport instance is not bound to Person instance.
    }

    fun showMe() = println("${passport.name} ${passport.surname}: $age")

}

fun main() {
    val passport = Person.Passport("John", "Doe")
    // We can create Passport object without Person object
    val person = Person(passport, 25)
    person.showMe() // John Doe: 25
    println(passport.isNameCorrect(person)) // true
}