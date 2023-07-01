package kotlinInDepth.classesAndObjects.properties.lateinit

import java.io.File

/*
class Content {
    var text: String? = null
    fun loadFile(file: File) {
        text = file.readText()
    }
}
 */

class Content {
    lateinit var text: String
    fun loadFile(file: File) {
        text = file.readText()
    }
    fun isTextInitialized() = this::text.isInitialized
}

fun getContentSize(content: Content) = content.text.length

// The property with a lateinit marker works just like an ordinary property
// short of a single difference. On attempt to read its value
// the program will check whether the property is initialized and throw
// UninitializedPropertyAccessException if it’s not.
/* text variable getter in Java:
   public final String getText() {
      String var10000 = this.text;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("text"); // throw exception if is not initialized.
      }
      return var10000;
   }

   public final void setText(String var1) {
      this.text = var1;
   }

   public final void loadFile(File file) {
      this.text = FilesKt.readText$default(file, (Charset)null, 1, (Object)null);
   }
 */
fun main() {
    val content = Content()
    println(content.isTextInitialized()) // false
    println(getContentSize(content)) // Exception

    // content.loadFile(/* Path here */)
    // println(getContentSize(content)) // OK
}

/* Lateinit rules:
* 1) Property must be mutable since its value may be changed
* in different parts of code.
*
* 2) It must have a non-nullable type and may not represent primitive
* value like Int or Boolean.
* The reason is that internally the lateinit property is represented
as a nullable variable with null reserved to mean ‘uninitialized’.
* For primitive types, there is no null value, so there is no way
* to mark a property as non-initialized and to provide the diagnostics that
* lateinit needs to provide.
* Therefore, lateinit is supported for properties of object types only.
*
* 3) lateinit property may not have an initializer since there will
* be no use in lateinit keyword.

 */

// Since Kotlin 1.2, it’s now possible to use late initialization
// for top-level properties and local variables.
lateinit var myText: String // OK