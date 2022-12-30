package kotlinInDepth.properties.accessors

class Person2(val firstName: String, val familyName: String) {
    var age: Int? = null
        set(value /* type is 'Int?' */) {
            if (value != null && value <= 0) {
                throw IllegalArgumentException("Invalid age: $value")
            }
            field = value
        }
}

fun main() {
    val person2 = Person2("John", "Doe")
    person2.age = 20 // calls the custom setter
    println(person2.age) // 20, uses a default getter

    person2.age = -1 // Exception: Invalid age: -1
}

// Note that the property initializer does not trigger a setter call
// since the initializer value is assigned to the backing field directly.

/* Decompiled code:
*
*  private Integer age;
   private final String firstName;
   private final String familyName;

   public final Integer getAge() {
      return this.age;
   }

   public final void setAge(@Nullable Integer value) {
      if (value != null && value <= 0) {
         throw (Throwable)(new IllegalArgumentException("Invalid age: " + value));
      } else {
         this.age = value;
      }
   }

   public final String getFirstName() {
      return this.firstName;
   }

   public final String getFamilyName() {
      return this.familyName;
   }

   public Person2(String firstName,String familyName) {
      super();
      this.firstName = firstName;
      this.familyName = familyName;
   }
 */