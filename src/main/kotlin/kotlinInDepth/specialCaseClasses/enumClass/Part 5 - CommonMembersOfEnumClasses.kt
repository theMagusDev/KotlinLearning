package kotlinInDepth.specialCaseClasses.enumClass

import kotlinInDepth.specialCaseClasses.enumClass.WeekDay.*

// All enum classes in Kotlin are implicit subtypes of the kotlin.Enum class
// which contains a set of common functions and properties available for any
// enum value.

// Any enum value has a pair of properties, ordinal and name which contain
// the zero-based index of its definition in the enum class body and
// value name, respectively.

enum class CompassDirection {
    NORTH, // name = NORTH, ordinal = 0
    SOUTH, // name = SOUTH, ordinal = 1
    WEST, // name = WEST, ordinal = 2
    EAST // name = EAST, ordinal = 3
}

fun program6() {
    CompassDirection.NORTH.name // NORTH
    CompassDirection.NORTH.ordinal // 0
    CompassDirection.EAST.name // EAST
    CompassDirection.EAST.ordinal // 3
}

// Values of a particular enum class are comparable with each other
// by ordinal property. Similarly to Java, the enum equality is based
// on their identity.

fun program7() {
    println(CompassDirection.WEST == CompassDirection.NORTH) // false
    println(CompassDirection.EAST > CompassDirection.NORTH) // true
    println(CompassDirection.SOUTH > CompassDirection.WEST) // false
}

// Each enum class also has a set of implicit methods which can be invoked on
// a class name similarly to members of a companion object. The valueOf()
// method returns an enum value given its name or throws an exception if a
// name is not valid.

fun program8() {
    println(CompassDirection.valueOf("NORTH")) // NORTH
    // println(CompassDirection.valueOf("NORTH_EAST")) // Exception: Invalid name
}

// The values() method gives you an array of all enum values in the order of
// their definition. Note that the array is created anew on each call so changes
// to one of them do not affect others.

/* Remember the WeekDay enum code:
 enum class WeekDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
 }
*/

private val weekDays = WeekDay.values()
val WeekDay.nextDay get() = weekDays[(this.ordinal + 1) % weekDays.size]

fun program9() {
    println(WeekDay.SATURDAY.nextDay) // SUNDAY
}

// Since Kotlin 1.1 you can use generic top-level functions enumValues() and
// enumValueOf() instead of values() and valueOf() methods respectively.

fun program10() {
    val weekDays = enumValues<WeekDay>()
    println(weekDays[2]) // WEDNESDAY
    println(enumValueOf<WeekDay>("THURSDAY")) // THURSDAY
}

fun main1() {
    program6()
    program7()
    program8()
    program9()
}