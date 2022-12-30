package kotlinInDepth.properties.accessors

/*
What about the direct field reference? It’s useful when you want your
property to be based on some stored value, but still needs to customize
access. For example, we could use it to log property reads.
 */

class Person1(val firstName: String, val familyName: String, age: Int) {
    val age: Int = age // must be initialized since it is having backing field.
        get(): Int {
            println("Accessing age")
            println("Field value is $field")
            return field // 'field' is the backing field property
        }
}

fun main() {
    val person1 = Person1("John", "Doe", 25)
    println(person1.age)
    // Accessing age
    // Field value is 25
    // 25

}

/* Decompiled code:
*
   private final int age;
   private final String firstName;
   private final String familyName;

   public final int getAge() {
      String var1 = "Accessing age";
      System.out.println(var1);
      var1 = "Field value is " + this.age;
      System.out.println(var1);
      return this.age;
   }

   public final String getFirstName() {
      return this.firstName;
   }

   public final String getFamilyName() {
      return this.familyName;
   }
 */
