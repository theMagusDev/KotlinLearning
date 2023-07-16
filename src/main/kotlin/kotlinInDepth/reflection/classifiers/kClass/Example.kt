package kotlinInDepth.reflection.classifiers.kClass

import kotlin.reflect.KCallable
import kotlin.reflect.full.allSuperclasses
import kotlin.reflect.full.superclasses

// Read after kClass Theory

// For example, in the following code, we use reflection to dynamically create
// an instance of the Person class and then call its fullName() function:
class Person(val name: String, val surname: String) {
    fun fullName(surnameFirst: Boolean = false): String {
        return if (surnameFirst) {
            "$surname $name"
        } else {
            "$name $surname"
        }
    }
}

fun program7() {
    val personClass = Class.forName("kotlinInDepth.reflection.classifiersAndTypes.Person").kotlin
    val person = personClass.constructors.first().call("John", "Doe")
    val fullNameFun: KCallable<*> = personClass.members.first { it.name == "fullName" }
    println(fullNameFun.call(person, false)) // John Doe
    //                         ^
    // Note that for members and extensions, the first parameter
    // is reserved for a receiver
}

// When KClass represents object declarations, the constructors property
// always returns you an empty collection. To obtain an actual instance, you
// may use the objectInstance property:
object O {
    val text = "Singleton"
}
fun program8() {
    println(O::class.objectInstance?.text ?: "Error: no object with name: O") // Singleton
}

// One more piece of information you can get from a KClass is supertype
// information provided by the supertypes property which returns a list of the
// KType instances.
interface IParent
interface IChild

open class GrandParent

open class Parent : GrandParent(), IParent

class Child : Parent(), IChild

fun program9() {
    println(Child::class.supertypes)
    /*
    [
     kotlinInDepth.reflection.classifiersAndTypes.Parent,
     kotlinInDepth.reflection.classifiersAndTypes.IChild
    ]
    */
    println(Child::class.superclasses)
    /*
    [
     class kotlinInDepth.reflection.classifiersAndTypes.Parent,
     class kotlinInDepth.reflection.classifiersAndTypes.IChild
    ]
     */

    println(Child::class.allSuperclasses)
    /*
    [
     class kotlinInDepth.reflection.classifiersAndTypes.Parent,
     class kotlinInDepth.reflection.classifiersAndTypes.GrandParent,
     class kotlin.Any,
     class kotlinInDepth.reflection.classifiersAndTypes.IParent,
     class kotlinInDepth.reflection.classifiersAndTypes.IChild
    ]
     */
}

fun main() {
    program7()
    program8()
    program9()
}