package kotlinInDepth.properties.accessors

// Since mutable properties have two accessors, they always possess
// a backing field unless both accessors are custom and do not reference
// it via the field keyword. For example, the age property has a
// backing field due to the default getter and direct mention
// in the setter, while the following property does not:
class Person3(var name: String, var surname: String) {
    var fullName: String
        get(): String = "$name $surname"
        // return name and surname
    set(value) {
        val names = value.split(" ") // Split string spaceseparated words
        if (names.size != 2) {
            throw IllegalArgumentException("Invalid full name: ‘$value’")
        }
        name = names[0]
        surname = names[1]
    }
    // set name and surname

    // so no need in backing field here.
}