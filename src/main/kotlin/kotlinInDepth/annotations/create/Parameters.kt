package kotlinInDepth.annotations.create

/*
 Following types of parameters are allowed to
be passed to an annotation's constructor:

 1) Primitive types (and their wrappers)
 2) Strings
 3) Classes
 4) Enums
 5) Other annotations
 6) Arrays of the types listed above

Nullable types are prohibited, because JVM does not
support storing null as a value of an annotation attribute.
 */

// annotation class Example(vararg val errorName: String?) // Error: Invalid type of annotation member

// If you pass an annotation as a parameter of another annotation,
// don't prefix it with the @ character!

// A new custom annotation
annotation class Special(val why: String)
// Passing Special annotation as parameter
annotation class Perfect(vararg val feature: Special)

// Simply put, think about passing it as a parameter
// like in the case of normal class instantiation.

fun main() {
    @Perfect(Special("Safe"), Special("Multi-paradigm"), Special("Concise"))
    val programmingLanguage = "Kotlin"
}

// To avoid confusion, applying an annotation is completely different
// from passing it as a parameter: to apply an annotation, you
// need to use the @ symbol.

// A new Custom annotation
annotation class Wow(val why: String)
// Passing Special annotation as parameter
// Applying @Special to this parameter
annotation class Amazing(@Wow("IDK") vararg val feature: Wow)

// Annotation parameter cannot be 'var':
// annotation class Example(var name: String) // Error
