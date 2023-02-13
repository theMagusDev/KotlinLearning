package kotlinInDepth.specialCaseClasses.dataClass.destructuringDeclarations

data class Human (
    val name: String,
    val surname: String,
    val age: Int
)

fun main() {
   val human = Human("Yuriy", "Magus", 17)
   val (name, surname, age) = human
}

/* Java
 public static final void main() {
      Human human = new Human("Yuriy", "Magus", 17);
      String name = human.component1();
      String surname = human.component2();
      int age = human.component3();
   }
 */