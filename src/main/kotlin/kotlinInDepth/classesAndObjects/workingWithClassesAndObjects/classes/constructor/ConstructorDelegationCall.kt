package kotlinInDepth.classes.constructor
// File #8

class Person10 {
    val fullName: String
    constructor(fullName: String) { // Secondary constructor #1
        this.fullName = fullName
    }
    constructor(name: String, surname: String) : this(fullName = "$name $surname") // delegating to Secondary constructor #1

    /* In Java:
    private final String fullName;

    public Person10(String fullName) {
      super();
      this.fullName = fullName;
    }

    public Person10(String name, String surname) {
       this(name + ' ' + surname);
    }
     */
}