package kotlinInDepth.functionalProgramming.extension.extensionFunctions

import kotlinInDepth.functionalProgramming.extension.extensionFunctions.somePackage.truncateString

// The Extension function is basically a function which can be called as if
// it were a member of some class. When you define such a function, you put a
// type of its receiver before its name separating them with a dot. Suppose we
// want to enrich the String type with a function which cuts the original
// string so that its length was not over a given length limit:
fun String.truncate(maxLength: Int): String {
//    ^         ^
// Receiver  Extension
//  type

    return if(this.length <= maxLength) this else this.substring(0, maxLength)
}

// Once defined, this function can be used just like any member
// of the String class.
fun program1() {
    println("Hello".truncate(10)) // Hello
    println("Hello".truncate(3)) // Hel
}

// Note that inside the extension function body, the receiver value can be
// accessed via this expression similar to class members. Members and
// extensions of the receiver can also be accessed implicitly without 'this'
// just like we’ve done with a substring() function call
// in the truncate() definition.
fun String.truncate2(maxLength: Int): String {
    return if(length <= maxLength) this else substring(0, maxLength)
    //        ^                              ^
    // 'this' can be omitted        'this' can be omitted

    // Just like in String's member function.
}

// It’s worth pointing out that extension functions by themselves are not able
// to break through the receiver type encapsulation. For example, since the
// extension function is defined outside the class, it can’t access
// its private members:
class Person(val name: String, private val age: Int)

// fun Person.showInfo() = println("$name $age")
// Error: can not access private 'age'

// The extension function, however, may be declared inside a class body
// making it a member and extension at the same time. Such function is
// allowed to access private members just like any other function
// in the class body:
class AnotherPerson(val name: String, private val age: Int) {
    fun AnotherPerson.showInfo() = println("$name $age")
}

// Extension functions can be used in bound callable references
// similar to class members:
fun Person.hasName(name: String) = name.equals(this.name, ignoreCase = true)
fun program2() {
    val hasNameFunctionRef = Person("John", 25)::hasName
    println(hasNameFunctionRef("JOHN")) // true
    println(hasNameFunctionRef("JAKE")) // false
}

// What if you have the function with the same signature defined both as a
// class member and as an extension? Consider the following code:
class Human(val name: String, val lastName: String) {
    fun fullName() = "$name $lastName"
}
fun Human.fullName() = "$name $lastName"

fun program3() {
    println(Human("John", "Doe").fullName()) // ???
    // The answer is: John Doe
    // Extension is shadowed by a member: public final fun fullName(): String

    // Favoring members over extensions prevents accidental modification of
    // existing class behavior which otherwise could have led to
    // hard-to-find errors. This also protects members of built-in
    // and JDK classes.
}

// Extension functions may be local. In particular, they may be nested into
// other extension functions. In such cases, this expression means the receiver
// of the innermost function. If you need to refer to the receiver of the outer
// function, instead you may use a qualified form of this which specifies the
// function name explicitly. This is also true for members of local classes or
// anonymous objects declared inside an extension function body:
private fun String.truncator(maxLength: Int) = object {
    val truncated
        get() = if (this@truncator.length <= maxLength) this@truncator else substring(0, maxLength)
    val original
        get() = this@truncator
}
fun program4() {
    val truncator = "Hello".truncator(3)
    println(truncator.original) // Hello
    println(truncator.truncated) // Hel
}
// The syntax is basically the same as we’ve seen
// in the case of inner classes.

// When the top-level extension function is defined in another package,
// it must always be imported before you can make a call. For example:

/* Code from somePackage/Util.kt
* package kotlinInDepth.functionalProgramming.extension.somePackage
*
* fun String.truncateString(maxLength: Int): String {
*     return if (length <= maxLength) this else substring(0, maxLength)
* }
 */

// Line #3 of the current file:
// import kotlinInDepth.functionalProgramming.extension.extensionFunctions.truncateString

fun program5() {
    println("Hello".truncateString(3))
}

// The reason is that such a function can’t be invoked by a qualified name
// since the qualifier position is taken by the receiver expression.

// Another explanation: extension functions are just static functions.
// So we have to import them as usual functions.

// See Util.kt and UnderTheHood.kt for more details.

//In other words, extension functions are essentially a syntactic sugar
// over ordinary functions which allow you to call them like class members.


// It’s worth noting that an extension function, unlike member functions 
// and properties, can be defined for a nullable receiver type. 
// Since nullable types do not have their own members, this mechanism 
// allows you to enrich them by introducing extension functions “from outside”. 
// Such extensions can then be invoked without the safe call operator:

// Nullable receiver
fun String?.truncateNullable(maxLength: Int): String? {
    if (this == null) return null 
    return if (this.length <= maxLength) this else this.substring(0, maxLength)

    // Note: ?. is not the safe call. The code in line #137 is equal to:
    // fun (String?).truncate(maxLength: Int): String
}
fun program6() {
    val s = readlnOrNull() // nullable String
    println(s.truncateNullable(3))
}

// Note that if the extension receiver has a nullable type, it’s 
// the responsibility of the extension function to handle a null value.

fun main() {
    program1()
    program2()
    program3()
    program4()
    program5()
    program6()
}