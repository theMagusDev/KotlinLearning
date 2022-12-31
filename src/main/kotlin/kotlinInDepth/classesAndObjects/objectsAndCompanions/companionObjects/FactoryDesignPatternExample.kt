package kotlinInDepth.objectsAndCompanions.companionObjects

// There are cases when using the constructor directly is unwanted.
// You can’t, for example, return null or instances of different types
// (conforming to the class type) depending on some pre-checks since
// a constructor call always returns an instance of its class or
// throws an exception. A possible solution is to mark a constructor as
// private, making it inaccessible outside the class, and define a
// nested object with a function which serves as a factory method
// and calls class constructor when necessary.

class OldApplication private constructor(val name: String) {
    object Factory {
        fun create(args: Array<String>): OldApplication? {
            val name = args.firstOrNull() ?: return@create null
            return OldApplication(name)
        }
    }
}

fun program1(args: Array<String>) {
    // Direct constructor call is not permitted
    // val app = Application(name)
    val app = OldApplication.Factory.create(args) ?: return@program1
    println("Application started: ${app.name}")
}

// Note that in this case, we have to refer to the object name every time
// we call the factory method unless it’s imported using the import
// Application.Factory.create directive. Kotlin allows you to solve this
// problem by turning the Factory object into the companion.

class NewApplication private constructor(val name: String) {
    companion object { // For a companion object, you can skip the name in the definition itself
        fun create(args: Array<String>): NewApplication? {
            val name = args.firstOrNull() ?: return@create null
            return NewApplication(name)
        }
    }
}

fun program2(args: Array<String>) {
    val app = NewApplication.create(args) ?: return@program2
    println("Application started: ${app.name}")

    // When the companion name is omitted, the compiler
    // assumes the default name Companion.
    val app2 = NewApplication.Companion.create(args) ?: return@program2
    // Companion reference is redundant, but there is no error.
}

/* Java bytecode:

public final class NewApplication {
   private final String name;
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);

   @NotNull
   public final String getName() {
      return this.name;
   }

   private NewApplication(String name) {
      this.name = name;
   }

   // $FF: synthetic method
   public NewApplication(String name, DefaultConstructorMarker $constructor_marker) {
      this(name);
   }

   public static final class Companion {
      @Nullable
      public final NewApplication create(String[] args) {
         String var10000 = (String)ArraysKt.firstOrNull(args);
         if (var10000 == null) {
            return null;
         } else {
            String name = var10000;
            return new NewApplication(name, (DefaultConstructorMarker)null);
         }
      }

      private Companion() {

      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}

 */