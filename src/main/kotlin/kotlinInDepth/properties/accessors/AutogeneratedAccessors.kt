package kotlinInDepth.properties.accessors

/* Immutable variables */
val a: Int = 5
/* Under the hood:

   private static final int a = 5;

   public static final int getA() {
      return a;
   }

*/

/* Mutable variables */
var b: Int = 15
/*
   private static int b = 15;

   public static final int getB() {
      return b;
   }

   public static final void setB(int var0) {
      b = var0;
   }
*/