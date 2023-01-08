package kotlinInDepth.functionalProgramming.extension.extensionFunctions.somePackage

fun String.truncateString(maxLength: Int): String {
    return if (length <= maxLength) this else substring(0, maxLength)
}

/* Java bytecode (simplified):

public final class UtilKt {

   public static final String truncateString(String $this$truncateString, int maxLength) {
      String result;
      if ($this$truncateString.length() <= maxLength) {
         result = $this$truncateString;
      } else {
         result = $this$truncateString.substring(0, maxLength);
      }

      return result;
   }
}

 */