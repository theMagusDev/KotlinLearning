package kotlinInDepth.classesAndObjects.understandingClassHierarchies.subclassInitialization

open class Person(val name: String, var age: Int)
class Student : Person {
    val university: String
    constructor(name: String, age: Int, university: String) : super(name, age) {
        this.university = university
    }
}

// Note the absence of parentheses after the superclass name: 'class Student : Person'
// instead of 'class Student : Person()'; the reason is that our class
// does not have a primary constructor and delegating is put
// into a secondary one instead.

// Unlike Java, calls between constructors – whether they
// belong to the same class, or class and its superclass – are never put into the
// constructor body. In Kotlin, you use a delegating call syntax for that.

