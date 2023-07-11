package kotlinInDepth.generics.typeParameters.boundsAndConstraints.constraint

/*
The type parameter syntax allows you to specify only one upper bound. In
some cases, though, we may need to impose multiple restrictions on a
single type parameter. This can be achieved by using a slightly more
elaborate syntax of type constraint. Suppose that we have a pair of
interfaces:
 */

interface Named {
    val name: String
}
interface Identified {
    val id: Int
}

// And we want to define a registry of objects which have
// both a name and an identifier:

class Registry<T> where T : Named, T: Identified {
    val users = ArrayList<T>()
}

fun <T>getFullData(obj: T) where T : Named, T: Identified {
    println("ID: ${obj.id}, name: ${obj.name}")
}

fun main() {
    class PCUser : Named, Identified {
        override val id: Int
            get() = 1
        override val name: String
            get() = "PCUser"
    }
    class Person

    val pcUser = PCUser()
    val usualPerson = Person()

//  getFullData(usualPerson) // Error: Required 'Named, Identified', found 'Person'
    getFullData(pcUser) // OK

//  val registry = Registry<Person>() // Error: Required 'Named, Identified', found 'Person'
    val registry = Registry<PCUser>() // OK
}
