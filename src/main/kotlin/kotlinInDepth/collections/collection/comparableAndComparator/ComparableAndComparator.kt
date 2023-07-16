package kotlinInDepth.collections.collection.comparableAndComparator

/*
Comparable instances possess a natural ordering. Each of them has the
compareTo() function which can be used to compare it with other instances
of the same type. So by making your type of an inheritor of Comparable,
you can automatically allow operations like < and > and, on top of it,
you can apply ordering operations.
 */

class Person (
    val name: String,
    val surname: String,
    var age: Int
) : Comparable<Person> /* Implements Comparable<Person> */ {
    val fullname: String
        get() { return "$name $surname" }

    override fun compareTo(other: Person): Int {
        if (name == other.name) {
            if (surname == other.surname) {
                return 0
            } else {
                return surname.compareTo(other.surname)
            }
        } else {
            return name.compareTo(other.name)
        }
    }

    /* Or in more simple way:
     override fun compareTo(other: Person) = fullName.compareTo(other.fullName)
     */

    override fun equals(other: Any?): Boolean {
        if (this !== other) {
            if (other is Person) {
                return name == other.name && surname == other.surname
            } else {
                return false
            }
        } else {
            return true
        }
    }

    override fun toString(): String {
        return "$name $surname $age"
    }
}

//  In many cases, a given class can be compared in multiple ways.
// For this reason, the Kotlin library provides a concept of comparator.

class AGE_COMPORATOR : Comparator<Person> {

    override fun compare(o1: Person?, o2: Person?): Int {
        val person1Age = o1?.age ?: return -1
        val person2Age = o2?.age ?: return 1
        return person1Age - person2Age
    }
}

fun main() {
    val SURNAME_COMPARATOR = Comparator<Person> { p1, p2 ->
        p1.surname.compareTo(p2.surname)
    }

    val persons = ArrayList<Person>()
    persons.add(Person("Yarik", "Tihonov", 25))
    persons.add(Person("Julia", "Semenova", 21))
    persons.add(Person("Gleb", "Ignat", 35))

    println(persons) // [Yarik Tihonov 25, Julia Semenova 21, Gleb Ignat 35]

    // Usual sorting
    persons.sort()
    println(persons) // [Gleb Ignat 35, Julia Semenova 21, Yarik Tihonov 25]

    persons.sortWith(AGE_COMPORATOR())
    println(persons) // [Julia Semenova 21, Yarik Tihonov 25, Gleb Ignat 35]

    persons.sortWith(SURNAME_COMPARATOR)
    println(persons) // [Gleb Ignat 35, Julia Semenova 21, Yarik Tihonov 25]

    // compareBy() and compareByDescending()
    val NAME_COMPARATOR = compareBy<Person> { it.name }
    val REVERSE_NAME_COMPARATOR = compareByDescending<Person> { it.name }
}



