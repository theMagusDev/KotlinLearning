package kotlinInDepth.classesAndObjects.understandingClassHierarchies.firstPart.subclassInitialization

open class Employee(val name: String, val age: Int) {
    open fun showInfo() {
        println("$name $age")
    }
    init {
        showInfo()
    }
}

class Teacher (name: String, age: Int, val specialization: String) : Employee(name, age) {
    override fun showInfo() {
        println("$name $age (specialization is $specialization)")
    }
}

fun main() {
    val teacher = Teacher("John", 27, "Computer science")
    // John 27 (specialization is null)

    // Why does the university variable happen to be null?
    //  The reason is that the method showInfo() is invoked
    //  in the superclass initializer. Program will call its
    // overriden version in Teacher class. But Employee
    // initialization happens before Teacher initialization,
    // when the property 'specialization' is not initialized
    // at the moment of the showInfo() call.

    // The reason this situation is called “leaking this” is because the
    // super class “leaks” the current instance to code which in general
    // may depend on the uninitialized part of the instance state. A more
    // explicit example is in file "LeakingThisExample":
}