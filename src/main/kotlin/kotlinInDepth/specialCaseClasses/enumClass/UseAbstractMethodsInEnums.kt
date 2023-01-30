package kotlinInDepth.specialCaseClasses.enumClass

enum class Seasons {
    SUMMER() { // Error if printTemperature() is not overridden.
        override fun printTemperature() {
            println("Hot")
        }
    },
    AUTUMN() { // Error if printTemperature() is not overridden.
        override fun printTemperature() {
            println("Ok")
        }
    },
    WINTER() { // Error if printTemperature() is not overridden.
        override fun printTemperature() {
            println("Cold")
        }
    },
    SPRING() { // Error if printTemperature() is not overridden.
        override fun printTemperature() {
            println("Ok")
        }
    };

    abstract fun printTemperature()
}

// Enum's constants can be not only values of enum's type, but also
// even classes (compiler generates them if they have methods, variables or
// some other class component).

/* Java bytecode
 public enum Seasons {
   SUMMER,
   AUTUMN,
   WINTER,
   SPRING;

   public abstract void printTemperature();

   private Seasons() {
   }

   public Seasons(DefaultConstructorMarker $constructor_marker) {
      this();
   }

   static final class SUMMER extends Seasons {
      public void printTemperature() {
         System.out.println("Hot");
      }

      SUMMER(String $enum$name, int $enum$ordinal) {
         super((DefaultConstructorMarker)null);
      }
   }

   static final class AUTUMN extends Seasons {
      public void printTemperature() {
         System.out.println("Ok");
      }

      AUTUMN(String $enum$name, int $enum$ordinal) {
         super((DefaultConstructorMarker)null);
      }
   }

   static final class WINTER extends Seasons {
      public void printTemperature() {
         System.out.println("Cold");
      }

      WINTER(String $enum$name, int $enum$ordinal) {
         super((DefaultConstructorMarker)null);
      }
   }

   static final class SPRING extends Seasons {
      public void printTemperature() {
         System.out.println("Ok");
      }

      SPRING(String $enum$name, int $enum$ordinal) {
         super((DefaultConstructorMarker)null);
      }
   }
}
 */