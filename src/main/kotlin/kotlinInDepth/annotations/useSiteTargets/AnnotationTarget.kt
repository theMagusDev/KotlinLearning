@file:Suppress("unused")
// Note that the same syntax is used for targeting the whole file above

package kotlinInDepth.annotations.useSiteTargets

// In the same manner, we can annotate the receiver parameter
// of an extension function:
fun @receiver:Cool String.capitalize() = println(this.uppercase())
// receiver (String) is annotated with @Cool

/*
A legitimate question is: which target will be used if we don't specify a
use-site target? According to the official documentation, it will
be chosen according to the applicable target annotation specified
by @Target. But what if there is more than one applicable target? In
that case, the first applicable target from the following list is used:

 1) param
 2) property
 3) field
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.VALUE_PARAMETER)
annotation class Great

class Tiger(@Great val name: String)

/*
    ...

   @NotNull
   private String name;

   @NotNull
   public final String getName() {
      return this.name;
   }

   public final void setName(@NotNull String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.name = var1;
   }

   public Cat(@Cool @NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      super();
      this.name = name;
   }

    ...


First, the compiler looks at @Target and finds three applicable
targets, then it applies the annotation to the first applicable
one from the above-mentioned list (param, property, field).

We can also use annotations on lambdas – they will be applied
to the invoke() method into which the lambda body is generated.
 */

val catName = @Cool { println("Barsik Cat") }

/*
If you want to apply an annotation to the primary constructor
of a class, then you must use the constructor keyword
and put the annotation before it:
 */

class Rabbit @Cool constructor(name: String)
