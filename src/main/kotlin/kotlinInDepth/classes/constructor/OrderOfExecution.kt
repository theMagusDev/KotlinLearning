package kotlinInDepth.classes.constructor
// File #7

/* Execution:
1) Primary constructor
2) Init blocks
3) Secondary constructor
 */
class Person9 {
    val name: String
    val surname: String

    init {
        println("Init block executed")
    }

    constructor(name: String, surname: String) { // secondary constructor #1
        this.name = name
        this.surname = surname
        println("Secondary constructor #1 block executed")
    }
    constructor(fullName: String) {
        val names: List<String> = fullName.split(" ")
        if (names.size != 2)
            throw IllegalArgumentException("Invalid name: $fullName")
        this.name = names[0]
        this.surname = names[1]
    }
}

fun main() {

    val person9 = Person9("Peter", "Mask")
    // Init block executed
    // Secondary constructor #1 block executed
}