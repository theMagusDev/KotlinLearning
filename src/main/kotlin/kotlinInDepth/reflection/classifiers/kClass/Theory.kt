package kotlinInDepth.reflection.classifiers.kClass

import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName

/*
In terms of Kotlin Reflection, a classifier is a declaration which defines a
type. Such declarations are represented by the KClassifier which has
2 inheritors:
 1) KClass<T> which represents a declaration of some class, interface, or
object with the compile-time type T.
 2) KTypeParameter which represents a type parameter of some generic
declaration.

There are two basic ways to obtain an instance of KClass.
The first it to use a class literal syntax:
 */

val StringKClass: KClass<String> = String::class


// Remember how reified works with inline functions:
inline fun <reified T> Any.cast() = this as? T
fun program1() {
    // From this:
    val obj: Any = "Hello"

    // From this:
    println(obj.cast<String>())

    // The compiler will actually generate a code:
    println(obj as? String)
} //             ^
//        Inlined function

// Also you can use ::class syntax to obtain a runtime class:
fun program2() {
    println((1+2)::class)  // KClass kotlin.Int
    println("abc"::class) // KClass kotlin.Int
}

// Also you can get KClass from instance of the java.lang.Class thanks
// to Kotlin's extension 'kotlin':
fun program3() {
    val stringClass = Class.forName("java.lang.String").kotlin
    println(stringClass is KClass<*>) // true
}

// Get java.lang.Class type instance:
fun program4() {
    val stringClass = String::class.java
    println(stringClass is Class<*>) // true
    println(stringClass is KClass<*>) // false
}

// For KClass API (functions, properties), see 'KClass API.jpg'

/* API Overview:
 'Kvisibility' is enum class with constants: PUBLIC, PROTECTED, INTERNAL, PRIVATE;
 The 'simpleName' property returns a simple name which was used in its
source code;
 The qualifiedName property gives you a full class' name including name
of the containing package;
 You can also use the 'jvmName' extension property which gives you a
qualified name of a class from the Java’s point of view:
 */
fun program5() {
    println(Any::class.qualifiedName) // kotlin.Any
    println(Any::class.jvmName) // java.lang.Object
}
/*
 The 'isInstance()' function allows you to check whether a given object is
an instance of a class represented by its receiver:
 */
fun program6() {
    println(String::class.isInstance("Hello")) // true
    println(String::class.isInstance(12)) // false
}
/*
The next group of the KClass properties provides access
to its member declarations:
 'constructors': This is a collection of both primary and secondary
constructors as instances of the KFunction type;
 'members': This is a collection of member functions and properties
represented by the KCallable instances, including all members
inherited from supertypes;
 'nestedClasses': This is a collection of nested classes and objects,
including companions;
 'typeParameters': This is a list of type parameters represented by the
KTypeParameter type;
 */

fun main() {
    program1()
    program2()
    program3()
    program4()
    program5()
    program6()
}
