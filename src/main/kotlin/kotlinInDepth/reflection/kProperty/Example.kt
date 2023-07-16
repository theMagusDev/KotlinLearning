package kotlinInDepth.reflection.kProperty

import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty1
import kotlin.reflect.jvm.isAccessible

/*
The KProperty interface adds checks for property-specific modifiers.
See 'KProperty API.jpg' for more details.
 */

val myValue = 1

fun program1() {
    println(::myValue.isConst) // false
    println(::myValue.isLateinit) // false

    println(::myValue.get()) // 1

    println(::myValue.getter) // getter of val myValue: kotlin.Int
    println(::myValue.getter()) // 1
}

/*
The KMutableProperty extends KProperty by adding a setter:
 */

var myMutableValue = 3

fun program2() {
    ::myMutableValue.set(5)
    println(::myMutableValue.get()) // 5

    ::myMutableValue.setter(10)
    println(::myMutableValue.get()) // 10
}

/*
The KProperty also has subtypes:
 KProperty0 representing properties without the receiver,
 KProperty1 representing properties with a single receiver (either dispatch, or extension),
 KProperty2 representing a pair of receivers (member extension),
 etc.

Another important case is callable references which always conform
to the proper functional type. For example:
 */

fun combine(number: Int, string: String) = "$number$string"

fun program3() {
    val combineFunction: KFunction2<Int, String, String> = ::combine
    //                                      ^
    //                            (Int, String) -> String
    println(combineFunction(1, "2")) // 12
}

/*
You can see that the callable reference in this example has
a KFunction2<Int, String, String> type which is a subtype
of (Int, String) -> String.

One more thing worth noting is the ability to access callables
with restricted visibility:
 */

class SecretHolder(private val secret: String)
fun program4() {
    val secretHolder = SecretHolder("Secret")
    val secretProperty: KProperty1<SecretHolder, String> = secretHolder::class.members
        .first(predicate = { it.name == "secret" }) as KProperty1<SecretHolder, String>
    secretProperty.isAccessible = true
    println(secretProperty.get(receiver = secretHolder)) // Secret
}

fun main() {
    program1()
    program2()
    program3()
    program4()
}
