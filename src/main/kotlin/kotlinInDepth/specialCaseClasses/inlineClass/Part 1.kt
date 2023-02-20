package kotlinInDepth.specialCaseClasses.inlineClass

/*
Creating wrapper classes is quite common in programming practice;

Suppose, for example, we want our program to have a concept of currency.
Although money quantity is essentially a number, we’d prefer not
to mix it with other numbers which may have a very different meaning.
So we will introduce some wrapper classes and utility functions:
 */

class OverheadDollar(val amount: Double)
class OverheadEuro(val amount: Double)
fun OverheadDollar.toEuro() = amount * 1.07
fun OverheadEuro.toDollar() = amount * 0.94

/* Java bytecode:
 ...
 public final class Dollar {

    // variable
    private final double amount;

    // getter
    public final double getAmount() {
       return this.amount;
    }

    // constructor
    public Dollar(double amount) {
       this.amount = amount;
    }
 }
 ...
 */

fun oldProgram() {
    val result = OverheadDollar(3.35).toEuro()
    println(result)
}

/*
 public static final void oldProgram() {
     double result = toEuro(new OverheadDollar(3.35));
     System.out.println(result);
 }

The problem with such an approach is a runtime overhead.
1) We create an extra object whenever we’re introducing a
new monetary amount.
2) The wrapped value is a primitive.

To solve such issues, Kotlin has introduced an inline class.
*/

@JvmInline
value class Dollar(val amount: Double)
// is similar to: inline class Dollar(val amount: Double)
inline class Euro(val amount: Double)
fun Dollar.toEuro() = amount * 1.07
fun Euro.toDollar() = amount * 0.94

/* Java:
public static final double toEuro-oBQZMw4(double $this$toEuro) {
      return $this$toEuro * 1.07;
   }

public static final double toDollar-g9bmHkk(double $this$toDollar) {
      return $this$toDollar * 0.94;
   }

public final class Dollar {
   private final double amount;

   public final double getAmount() {
      return this.amount;
   }

   // constructor
   private Dollar(double amount) {
      this.amount = amount;
   }

   public static double constructor-impl(double amount) {
      return amount;
   }

   // Synthetic methods
   public static final Dollar box-impl(double amount) {
      return new Dollar(amount);
   }

   public static String toString-impl(double amount) {
      return "Dollar(amount=" + amount + ")";
   }

   public static int hashCode-impl(double amount) {
      return Double.hashCode(amount);
   }

   public static boolean equals-impl(double amount, Object var2) {
      ...
   }

   public static final boolean equals-impl0(double p1, double p2) {
      return Double.compare(p1, p2) == 0;
   }

   // get
   public final double unbox-impl() {
      return this.amount;
   }

   // Standard methods calling declared above.

   public String toString() {
      return toString-impl(this.amount);
   }

   public int hashCode() {
      return hashCode-impl(this.amount);
   }

   public boolean equals(Object var1) {
      return equals-impl(this.amount, var1);
   }
}
 */

/*
Such a class MUST have a single immutable property declared in the primary
constructor. At runtime, a class instance will be represented as a value of
this property without creating any wrapper objects. Similarly, inline functions whose bodies are substituted
instead of their calls, the data contained in an inline class is substituted
instead of its usages.
 */

fun program1() {
    val result = Dollar(3.35).toEuro()
    println(result)
}

/*
 public static final void program1() {
     double result = toEuro-oBQZMw4(Dollar.constructor-impl(3.35));
     System.out.println(result);
 }
*/

/*
Inline classes may have their own properties and functions.
Inline class properties, however, may not have any state.
This means that no backing fields, no lateinit, or
delegated (including lazy) properties are possible.
Inline class properties may have only explicit accessors.
No initializers are allowed.
 */