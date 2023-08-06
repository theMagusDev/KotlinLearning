package kotlinInDepth.functionalProgramming.functionReference

/* In general, there are four kinds of function references:
* reference to a function;
* reference by Class;
* reference by Object;
* reference to a constructor.
 */

// ref to a fun: "::functionName"

fun add(x: Int, y: Int) = x + y
fun program1() {
    val operatorAdd: (Int, Int) -> Int = ::add
    println(operatorAdd(2, 3)) // 5
}

// ref by class: "ClassName::functionName"

val inc: (Int) -> Int = Int::inc
fun program2() {
    println(inc(2)) // 3
}

// Reference by Object: "objectName::functionName"
val whatsGoingOnText: String = "What's going on here?"
val indexWithinWhatsGoingOnText: (text: String, startPos: Int, ignoreCase: Boolean) -> Int =
    whatsGoingOnText::indexOf
// equal to:
val indexWithinWhatsGoingOnText1: (String, Int, Boolean) -> Int =
    { string: String, startIndex: Int, ignoreCase: Boolean -> whatsGoingOnText.indexOf(string, startIndex, ignoreCase) }
fun program3() {
    println(indexWithinWhatsGoingOnText("going", 0, false)) // 7
    println(indexWithinWhatsGoingOnText("Hi", 0, false)) // -1

    println(indexWithinWhatsGoingOnText) // function indexOf (Kotlin reflection is not available)
    // because indexWithinWhatsGoingOnText references to the indexOf function.
}

// Reference to a constructor: "::ClassName"
class Citizen (val name: String)

val citizenGenerator: (String) -> Citizen = ::Citizen
val citizenGenerator1: (String) -> Citizen = { name: String -> Citizen(name) } // the same

fun program4() {
    val johnFoster: Citizen = citizenGenerator("John Foster")
}

fun main() {
    program1()
    program2()
    program3()
}