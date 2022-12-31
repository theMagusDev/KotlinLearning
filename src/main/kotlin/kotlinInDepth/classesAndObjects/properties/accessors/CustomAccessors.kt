package kotlinInDepth.properties.accessors

class Person(val name: String, val surname: String) {

    // Defining an accessor which
    // is used to read a property value:
    val fullName: String
        get(): String {
            return "$name $surname"
        }

    // Similar to functions, accessors support an expression-body form.
    val reversedFullName: String
        get(): String = "$surname $name"

    // Getter's return type must be the same as property's or can be omitted.
    val doubleName
        get() = "$name $name" // String is inferred
}

fun main() {
    val person = Person("John", "Doe")
    println(person.fullName) // getter was called, compute the property. Output: John Doe
}

/*
* The value of the fullName property we’ve introduced earlier is computed
* on each access. Unlike firstName and familyName, it doesn’t have a
* backing field and thus doesn’t occupy memory in a class instance. In other
* words, it’s basically a function which simply has a property form. In Java, we’d usually
* introduce a method like getFullName() for the same purpose (without the 'fullName' variable).
* The rule regarding backing fields is as follows: the backing field is
* generated when a property has at least one default accessor or a custom
* accessor which explicitly mentions the field. Since immutable properties
* have only one accessor, a getter, and in our example, it doesn’t reference the
* backing field directly, the fullName property will have no backing field.
*
* Decompiled code:

   private final String name;

   private final String surname;

   public final String getFullName() {
      return this.name + ' ' + this.surname;
   }

   public final String getReversedFullName() {
      return this.surname + ' ' + this.name;
   }

   public final String getDoubleName() {
      return this.name + ' ' + this.name;
   }

   public final String getName() {
      return this.name;
   }

   public final String getSurname() {
      return this.surname;
   }
*
*/

// In other words, there is no fullName variable created.
// Compiler just generates getFullName() method to compute the result.

// When a property doesn’t use a backing field, it can’t have an initializer
// because the initializer is basically a value assigned directly to the backing
// field on initialization of a class instance. That’s why we didn’t add the
// initializer for the fullName definition above: being a computed property it
// doesn’t need one.