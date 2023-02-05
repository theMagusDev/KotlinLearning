package kotlinInDepth.specialCaseClasses.enumClass

// Similarly to other classes, enums may have their own members. Besides
// that, you can define your own extension functions and properties as
// evidenced by the preceding example.

// The enum class may include any definitions permitted for an ordinary class,
// including functions, properties, primary and secondary constructors,
// initialization blocks, inner/non-inner nested classes, and objects (whether
// companion or not). Any such declarations in an enum class body must be
// placed after the enum constant list. The constant list itself in this case must
// be terminated by a semicolon. The members declared in an enum class body
// are available for all its constants:

enum class DayOfTheWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    val lowerCaseName get() = name.lowercase()
    // Note: 'name' var returns the name of this enum constant, exactly as declared in its enum declaration.

    fun isWorkDay() = !(this == SATURDAY || this == SUNDAY)
}

fun program3() {
    println(DayOfTheWeek.WEDNESDAY.isWorkDay()) // true
    println(DayOfTheWeek.WEDNESDAY.lowerCaseName) // wednesday
}

// When an enum class has a constructor, you need to place an
// appropriate call in the definition of each enum constant:
enum class RainbowColor(val isCold: Boolean) {
    RED(isCold = false),
    ORANGE(isCold = false),
    YELLOW(isCold = false),
    GREEN(isCold = true),
    BLUE(isCold = true),
    INDIGO(isCold = true),
    VIOLET(isCold = true);

    val isWarm = !this.isCold
}

fun program4() {
    println(RainbowColor.BLUE.isCold) // true
    println(RainbowColor.ORANGE.isWarm) // true
}

// The enum constants may also have a body with their own definitions. Note,
// however, that an anonymous type introduced by such constants
// are not exposed to the outside code.

enum class AnotherDayOfTheWeek {
    MONDAY { fun startWork() = println("Work week started") },
    TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

fun program5() {
    // println(AnotherDayOfTheWeek.MONDAY.startWork())
    // Error: Unresolved reference: startWork
}

/* Java bytecode:
 public enum AnotherDayOfTheWeek {
   MONDAY,
   TUESDAY,
   WEDNESDAY,
   THURSDAY,
   FRIDAY,
   SATURDAY,
   SUNDAY;

   private AnotherDayOfTheWeek() {
   }

   public AnotherDayOfTheWeek(DefaultConstructorMarker $constructor_marker) {
      this();
   }

   static final class MONDAY extends AnotherDayOfTheWeek {
      public final void startWork() {
         System.out.println("Work week started");
      }

      MONDAY(String $enum$name, int $enum$ordinal) {
         super((DefaultConstructorMarker)null);
         // calling constructor of line #75
      }
   }
}
 */

// Such members are generally helpful when they are used to provide the
// implementation of virtual methods in the enum class itself or some
// supertype. We’ll defer such examples till Chapter 8.
// Note that currently all nested classes defined in an enum constant body
// must be inner.

enum class AnotherDayOfTheWeek2 {
    MONDAY {
        // class Morning { fun getUp() = print("Get up! Its Monday!") } // Error
        inner class Morning { fun getUp() = print("Get up! Its Monday!") }
    },
    TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

fun main() {
    program3()
    program4()
    program5()
}