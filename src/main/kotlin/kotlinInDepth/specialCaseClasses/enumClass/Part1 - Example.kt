package kotlinInDepth.specialCaseClasses.enumClass

import kotlinInDepth.specialCaseClasses.enumClass.underTheHood.WeekDay

enum class WeekDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

// Note: all enum's constants are public static final variables of type WeekDay
// For more info see 'underTheHood' package.

fun WeekDay.isWorkDay() =
    !(this == WeekDay.SATURDAY || this == WeekDay.SUNDAY)

fun program1() {
    println(WeekDay.MONDAY.isWorkDay()) // true
    println(WeekDay.SATURDAY.isWorkDay()) // false
}

// Enums are somewhat similar to object declarations in a sense that they
//define a set of global constants representing instances of a particular type.
//Similarly to objects, they are not permitted in contexts where there is no
//guarantee that such a definition can be available as a global constant. You
//can’t, for example, put an enum definition into an inner class or function
//body:

fun program2() {
    // enum class Test { A, B, C, D } // Error
}

/* Main function */
fun main() {
    program1()
}