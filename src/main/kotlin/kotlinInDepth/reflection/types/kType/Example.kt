package kotlinInDepth.reflection.types.kType

import kotlin.reflect.KType

fun main() {
    val nothingSuperTypes: List<KType> = Nothing::class.supertypes

    for (kType in nothingSuperTypes) {
        println("$kType is nullable: ${kType.isMarkedNullable}")
        println("$kType's classifier is ${kType.classifier}")
        println("$kType's arguments are ${kType.arguments}")
        println()
    }
    /*
     kotlin.Any is nullable: false
     kotlin.Any's classifier is class kotlin.Any
     kotlin.Any's arguments are []
     */

    val stringSuperTypes: List<KType> = String::class.supertypes

    for (kType in stringSuperTypes) {
        println("$kType is nullable: ${kType.isMarkedNullable}")
        println("$kType's classifier is ${kType.classifier}")
        println("$kType's arguments are ${kType.arguments}")
        println()
    }
    /*
     kotlin.Comparable<kotlin.String> is nullable: false
     kotlin.Comparable<kotlin.String>'s classifier is class kotlin.Comparable
     kotlin.Comparable<kotlin.String>'s arguments are [kotlin.String]

     kotlin.CharSequence is nullable: false
     kotlin.CharSequence's classifier is class kotlin.CharSequence
     kotlin.CharSequence's arguments are []

     java.io.Serializable is nullable: false
     java.io.Serializable's classifier is class java.io.Serializable
     java.io.Serializable's arguments are []

     kotlin.Any is nullable: false
     kotlin.Any's classifier is class kotlin.Any
     kotlin.Any's arguments are []
     */
}