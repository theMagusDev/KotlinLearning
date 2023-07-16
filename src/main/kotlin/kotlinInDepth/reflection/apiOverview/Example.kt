package kotlinInDepth.reflection.apiOverview

import kotlin.reflect.KClass

fun theory() {/* Short theory

    'CLASS_NAME::class' returns 'KClass<CLASS_NAME>' object:
    */
    val componentExample: KClass<Main> = Main::class

    /*
    'CLASS_NAME::class.java' returns 'java.util.Class<CLASS_NAME>' object:
     */
    val componentAnotherExample: Class<Main> = Main::class.java
}

/*
Java vs Kotlin: Note that Kotlin Reflection is not self-sufficient. In some
cases like class search and loading, we have to rely on the facilities
provided by the Java Reflection API. When it comes to manipulating
Kotlin-specific aspects of your code (like properties or objects), using the
Kotlin API gives you a more concise and idiomatic way to access them at runtime.

The Reflection classes reside in the kotlin.reflect package and can be
divided into two basic groups: callables which deal with the
representation of properties and functions (including constructors) and
classifiers which provide a runtime representation of classes and type
parameters. See 'Reflection hierarchy.png' for more details.

All reflection types are inheritors of KAnnotatedElement which allows
you to access annotations defined for a particular language element such as
a function, property, or class. KAnnotatedElement has a single property
which returns a list of annotation instances:
 */

annotation class Dependency(vararg val componentClasses: KClass<*>)
annotation class Component(
    val name: String, val dependency: Dependency = Dependency()
)

@Component(name = "I/O")
class IO

@Component(name = "Log", Dependency(IO::class))
class Logger

@Component(name = "Core", dependency = Dependency(IO::class, Logger::class))
class Main

// Suppose that we want to retrieve annotations associated with the Main
// class. We can do so by using the annotations property on its class literal:

fun main() {
    val component: Component = Main::class.annotations
        .filterIsInstance<Component>()
        .firstOrNull() ?: return@main
    println("Component name: ${component.name}")

    val dependencies = component.dependency.componentClasses
        .joinToString { it.simpleName ?: "" }
    println("Dependencies: $dependencies")

    /*
     Component name: Core
     Dependencies: IO, Logger
     */
}
