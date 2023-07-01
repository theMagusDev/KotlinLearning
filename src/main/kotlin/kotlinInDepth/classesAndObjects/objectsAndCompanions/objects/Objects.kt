package kotlinInDepth.objectsAndCompanions.objects

object Application { // a singleton
    val name = "My Application"
    override fun toString() = name
    fun exit() {  }
}

// Such object declaration can be used as both a class
// and a value representing its instance.

fun describe(app: Application) = Application.name // Application as a type

fun main() {
    println(Application) // My Application
}

// Using an object as a type is usually meaningless
// since such a type has exactly one instance, so you can just
// as well refer to that instance itself.

// Note: The initialization itself happens lazily on the loading
// of the singleton class which usually happens when
// the program first refers to the object instance.

/* Application.java in Java:
*
public final class Application {

   private static final String name;
   public static final Application INSTANCE;

   public final String getName() {
      return name;
   }

   public String toString() {
      return name;
   }

   public final void exit() {

   }

   private Application() {

   }

   static {
      INSTANCE = new Application();
      name = "My Application";
   }
}
*
* describe and main methods:

   public static final String describe(@NotNull Application app) {
      return app.getName();
   }

   public static final void main() {
      System.out.println(Application.INSTANCE);
   }

 */

// Similar to classes, object declarations can include member functions
// and properties as well as initializer blocks, but may not have primary
// or secondary constructors. An object instance is always
// created implicitly so constructor calls make no sense for objects.

// Classes in the object body can’t be marked as inner.
// Instances of inner classes are always associated with
// the corresponding instance of their enclosing class, but
// object declarations have only one instance which makes the
// inner modifier effectively redundant. That’s the reason why
// it is forbidden.