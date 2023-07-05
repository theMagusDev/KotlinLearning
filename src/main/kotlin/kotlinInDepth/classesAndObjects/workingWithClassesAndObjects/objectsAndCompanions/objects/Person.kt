package kotlinInDepth.objectsAndCompanions.objects

import kotlin.random.Random

// Like classes, objects can be nested into other classes or
// even into other objects. Such declarations are also singletons
// which have exactly one instance per entire application.

object UniquePerson {
    object Passport
}

// If you need a separate instance per enclosing
// class, you should use an inner class instead.

class Person {
    val passport: Passport = Passport()

    inner class Passport {
        val ID = Random.nextInt(11111, 99999)
    }
}

fun main() {
    println(UniquePerson.Passport)

    val person = Person()
    println("Person's ID: ${person.passport.ID}")
}

// You can’t, however, put objects inside functions as well as
// local or inner classes because such definitions in general
// would depend on some enclosing context and thus
// could not be singletons.

fun abc() {
    // object SomeObject // Error: Named object 'SomeObject' is a singleton and cannot be local.
}
// Locally-scoped objects can be created using an object
// expression which we’ll consider later.