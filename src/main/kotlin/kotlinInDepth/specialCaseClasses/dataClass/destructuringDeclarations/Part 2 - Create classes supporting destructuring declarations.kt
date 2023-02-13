package kotlinInDepth.specialCaseClasses.dataClass.destructuringDeclarations

class OldDog (
    val name: String,
    val age: Int
)

fun program2() {
    // val (name, age) = OldDog("Nik", 7) Error:
    // Destructuring declaration initializer of type OldDog must have a 'component1()' function
    // Destructuring declaration initializer of type OldDog must have a 'component2()' function
}

class NewDog (
    val name: String,
    val age: Int
) {
    operator fun component1(): String {
        return name
    }
    operator fun component2(): Int {
        return age
    }
}

fun program3() {
    val (name, age) = NewDog("Nik", 7) // OK
}