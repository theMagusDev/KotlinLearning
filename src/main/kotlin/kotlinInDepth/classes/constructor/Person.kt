package kotlinInDepth.classes.constructor
// File #1

class Person (name: String, surname: String) {
    val fullName = "$name $surname"
}

/* Java:

   private final String fullName;

   public final String getFullName() {
      return this.fullName;
   }

   public Person(String name, String surname) {
      super();
      this.fullName = name + ' ' + surname;
   }

 */

fun abc() {
    val person = Person("Ivan", "Hobs")
    println(person.fullName) // Ivan Hobs
    // println(person.name) // Error
}

/* Create properties in constructor */

class Person1 (val name: String, val surname: String) {
    val fullName = "$name $surname"
}

/* Java

   private final String fullName;
   private final String name;
   private final String surname;

   public final String getFullName() {
      return this.fullName;
   }
   public final String getName() {
      return this.name;
   }
   public final String getSurname() {
      return this.surname;
   }

   public Person1(String name, String surname) {
      super();
      this.name = name;
      this.surname = surname;
      this.fullName = this.name + ' ' + this.surname;
   }

 */

fun abc1() {
    val person1 = Person1("Ivan", "Hobs")
    println(person1.fullName) // Ivan Hobs
    println(person1.name) // Ivan
    println(person1.surname) // Hobs
}