package selfLearning.generics.intro

import kotlinInDepth.specialCaseClasses.dataClass.dataClassesAndTheirOperations.Car

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        val list: MutableList<Any?> = ArrayList() // raw use, type is Object
        list.add("OK")
        list.add(5)
        list.add(Car("BMW", 185, "AA001A123"))

        // We can add anything we want, this is a raw use.
        // What problems we can face?
        val strings: MutableList<Any?> = ArrayList()
        strings.add("Hello")
        strings.add("How are you?")
        strings.add("Bye.")
        strings.add(5) // fake string

//      for (String s : list) { } // Error: type is Object, not String
        for (o in list) {
//          System.out.println(o + " length = " + o.length());
            // Error: Object does not have length()
        }
        for (o in list) {
            println(o.toString() + " length = " + (o as String).length)
            // Works, but '5' will throw a class cast exception.
        }

        // That is why we shouldn't use raw type.

        val realStrings: MutableList<String> = ArrayList<String>()
        realStrings.add("Hello")
        realStrings.add("How are you?")
        realStrings.add("Bye.")
//      realStrings.add(5); // fake string: Error now.
        val anotherRealStrings: List<String> = ArrayList()
        // You can omit type in second diamonds.
    }
}