package kotlinInDepth.classesAndObjects.classes.inner

// Note: Inner nested classes object is bound to outer classes object

class Person (name: String, surname: String, age: Int) {
    val information: Information = this.Information(name, surname, age)
    // equal to: val information: Information = Information(name, surname, age)
    private val secret = 123

    inner class Information(var name: String, private var surname: String, var age: Int) {
        fun getSurname() = surname
        fun getSecret() = this@Person.secret // can access private elements of outer class's instance
    }

    fun showMe() = println("${information.name} ${information.getSurname()}: ${information.age}")

}

fun program1() {
    val person = Person("John", "Doe", 25)
    person.showMe() // John Doe: 25
    println(person.information.getSurname()) // true
    // person.name // Error
    person.information.age // OK
}

/* Create information after person creation */

class Person2 (var name: String, var surname: String, var age: Int) {

    inner class Information(var name: String, private var surname: String, var age: Int)

    fun showMe() = println("$name} ${surname}: $age")

}

fun program2() {
    // Create information after object
    val person2 = Person2("John", "Doe", 25)
    person2.Information("John", "Doe", 25)
    person2.showMe()
}

class Outer() {
    val a = 123

    inner class Inner() {
        val a = 321
        fun getAFromInner() = this.a
        // 'this' refers to the current instance of an inner class itself
        fun getAFromOuter() = this@Outer.a
        // 'this@Outer' refers to the current instance of an outer class
    }
}

fun main() {
    program1()
    program2()
}