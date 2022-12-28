package kotlinInDepth.classes.constructor
// File #6

class Person8 {
    val name: String
    val surname: String

    constructor(name: String, surname: String) { // public by default
        this.name = name
        this.surname = surname
    }
    constructor(fullName: String) {
        val names: List<String> = fullName.split(" ")
        if (names.size != 2)
            throw IllegalArgumentException("Invalid name: $fullName")
        this.name = names[0]
        this.surname = names[1]
        return // can be used here, because constructors return Unit
    }

    // Note that secondary constructors may not declare property-parameters
    // using the val/var keywords:
    // constructor(val fullName: String) // Error
}
