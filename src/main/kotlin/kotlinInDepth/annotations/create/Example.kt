package kotlinInDepth.annotations.create

// Declaring an annotation is similar to declaring a class – just add the annotation modifier in front of it:
annotation class ExampleAnnotation

// Annotations can also have constructors that take parameters:
annotation class CustomSuppress(vararg val errorName: String)

fun main() {
    @CustomSuppress("UNUSED_VARIABLE")
    val userName = "Alex"

    @CustomSuppress // is also correct since vararg means zero or more args
    val phone = 1234567
}
