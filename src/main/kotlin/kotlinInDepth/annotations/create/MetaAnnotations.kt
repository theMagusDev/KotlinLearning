package kotlinInDepth.annotations.create

/*
We can also use annotations to provide metadata about other annotations.
The annotations that annotate other annotations are called "Meta-annotations".
Meta-annotations are the following:
 */

// 1) @Target: indicates which kinds of elements can be targeted

@Target(AnnotationTarget.LOCAL_VARIABLE)
annotation class SpecialVariable

// @SpecialVariable // Error
fun program1() {

//  @SpecialVariable // Error
    class Example()

    @SpecialVariable
    val someInteger = 7 // OK

//  println(@SpecialVariable someInteger)
    // Error: This annotation is not applicable to target 'expression'
}

// You can also target as many elements as you want:
@Target(
    AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.CLASS
)
annotation class Cool

/*
If the @Target meta-annotation is not present in the annotation declaration,
the annotation is applicable to the following elements by default:

CLASS
PROPERTY
FIELD
LOCAL_VARIABLE
VALUE_PARAMETER
CONSTRUCTOR
FUNCTION
PROPERTY_GETTER
PROPERTY_SETTER.
 */


// 2) @Retention: define annotation's lifecycle
/*
SOURCE
Annotation isn't stored in binary output.

BINARY
Annotation is stored in binary output but is invisible for reflection.

RUNTIME
Annotation is stored in binary output and is visible for reflection (default retention).
 */

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class VeryCool
// This annotation can be applied to functions only and won't be stored in
// binary output and will be deleted when the program runs.


// 3) @Repeatable: add an ability to apply an annotation to the same element multiple times

@Repeatable
annotation class Best

fun main() {
    @Best
    @Best
    @Best
    val name = "Kotlin"
}

// 4) @MustBeDocumented: specifies that the annotation is a part of public API
// and therefore should be included in the generated documentation for
// the element to which the annotation is applied.
