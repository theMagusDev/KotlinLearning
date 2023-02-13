package kotlinInDepth.specialCaseClasses.dataClass.dataClassesAndTheirOperations

data class Car (
    val model: String,
    val horsePower: Int,
    val licencePlate: String,
) {
    fun engineOn() = "Starting..."
}

/* Java:
public final class Car {
   private final String model;
   private final int horsePower;
   private final String licencePlate;

   public final String engineOn() {
      return "Starting...";
   }

   /* Auto-generated getters */

   public final String getModel() {
      return this.model;
   }

   public final int getHorsePower() {
      return this.horsePower;
   }

   public final String getLicencePlate() {
      return this.licencePlate;
   }

   /* Primary constructor */

   public Car(String model, int horsePower, String licencePlate) {
      super(); // calling constructor with no arguments
      this.model = model; // setting up properties
      this.horsePower = horsePower;
      this.licencePlate = licencePlate;
   }

   /* Auto-generated componentN() methods */

   public final String component1() {
      return this.model;
   }

   public final int component2() {
      return this.horsePower;
   }

   public final String component3() {
      return this.licencePlate;
   }

   // These componentN methods just return their fields.
   // The component number follows the order of the constructor parameters.


   /* Auto-generated copy() methods */

    // if all arguments are present:
   public final Car copy(String model, int horsePower, String licencePlate) {
      return new Car(model, horsePower, licencePlate);
   }

   // synthetic method if some arguments are missing:
   public static Car copy$default(Car car, String model, int horsePower, String licencePlate, int var4, Object var5) {
      if ((var4 & 1) != 0) { // if 'model' is not passed,
         model = car.model; // then just copy it.
      }

      if ((var4 & 2) != 0) {
         horsePower = car.horsePower;
      }

      if ((var4 & 4) != 0) {
         licencePlate = car.licencePlate;
      }

      return car.copy(model, horsePower, licencePlate);
   }


   /* Auto-generated toString() */

   public String toString() {
      return "Car(model=" + this.model + ", horsePower=" + this.horsePower + ", licencePlate=" + this.licencePlate + ")";
   }


   /* Auto-generated toString() */

   public int hashCode() {
      int var1 = ((this.model != null ? this.model.hashCode() : 0) * 31
      int var2 = Integer.hashCode(this.horsePower)) * 31;
      int var3 = (this.licencePlate != null ? this.licencePlate.hashCode() : 0);
      return var1 + var2 + var3
   }
   // Remember ?: syntax:
   // int max = a > b ? a : b


   public boolean equals(Object another) {
      if (this != another) {
         if (another instanceof Car) { // then compare properties of two instances of Car class
            Car anotherCar = (Car)another;
            if (Intrinsics.areEqual(this.model, anotherCar.model) && this.horsePower == anotherCar.horsePower && Intrinsics.areEqual(this.licencePlate, anotherCar.licencePlate)) {
               return true;
            }
         }

         return false;
      } else { // ref to the same object, so return 'true'
         return true;
      }
   }
}
 */