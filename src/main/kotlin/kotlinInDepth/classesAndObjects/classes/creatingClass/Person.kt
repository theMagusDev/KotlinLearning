package kotlinInDepth.classes.creatingClass

class Person() { // class is public by default
    var name: String = ""
    /* The line above is equal to Java's:

    private String name = "";
    // All fields are private in Kotlin (putting 'public' will change nothing)

    // But getters and setters are public by default, so we can use them in main()
    // Auto-generated getter
    @NotNull
    public final String getName() {
       return this.name;
    }

    // Auto-generated setter
    public final void setName(@NotNull String var1) {
       Intrinsics.checkNotNullParameter(var1, "<set-?>");
       this.name = var1;
    }

    */
    var surname: String = ""
    var age: Int = 0
    val type = "Human" // all objects will have type = "Human" since it is immutable

    fun fullName() = "${this.name} ${this.surname}"
    // is the same as: fun fullName() = "$firstName $surname"

    fun showMe() = println("${fullName()}: $age")
    fun readAge() {
        this.age = readln().toInt()
    }
    fun setNameAndSurname(firstName: String, surname: String) {
        this.name = firstName
        this.surname = surname
    }
}

fun main() {
    val person = Person()
    person.name = "John"
    person.surname = "Doe"
    person.age = 25
    person.showMe() // John Doe: 25
}

private class Test1 // classes can be private in Kotlin