package kotlinInDepth.reflection.callables.kFunction

import kotlin.reflect.KCallable

/*
'callable' unites properties and functions which you can call
to obtain some result. In the Reflection API, they are represented by a
generic interface KCallable<out R> where R denotes either
the return type of a function or a type of a property.
 */

// One way to get an instance of KCallable is to use callable references:
fun combine(number: Int, string: String) = "$number$string"
fun program1() {
    val combineFun = ::combine // type:  KFunction2<Int, String, String>
    println(::combine) // fun combine(kotlin.Int, kotlin.String): kotlin.String
    println("${::combine.name} return type: ${::combine.returnType}") // combine return type: kotlin.String
    println("${::combine.name} is abstract: ${::combine.isAbstract}") // combine is abstract: false
    println("${::combine.name} is final: ${::combine.isFinal}") // combine is final: true
    println("${::combine.name}'s visibility: ${::combine.visibility}") // combine's visibility: PUBLIC
}

// Note that for members and extensions, the first parameter
// is reserved for a receiver (see example in classifiers.kClass.Example).
// When callable is a member and extension at the same time, the
// first parameter is reserved for dispatch receiver (its owner class type)
// and the second one is reserved for extension receiver. For example:

val simpleVal = 1
val Int.extensionGetter get() = this
class A {
    val Int.memberExtensionGetter get() = this
}

fun program2() {
    fun printCallableParams(callable: KCallable<*>) {
        println(
            callable.parameters.joinToString(
                prefix = "[",
                postfix = "]",
                transform = { it.type.toString() } // it: KParameter
            )
        )
    }

    printCallableParams(::simpleVal) // []
    printCallableParams(Int::extensionGetter) // [kotlin.Int]
    printCallableParams(A::class.members.first(predicate = { it.name == "memberExtensionGetter" }))
    // [kotlinInDepth.reflection.callables.kFunction.A, kotlin.Int]
}

fun main() {
    program1()
    program2()
}
