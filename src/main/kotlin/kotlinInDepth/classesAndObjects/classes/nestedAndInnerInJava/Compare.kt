package kotlinInDepth.classesAndObjects.classes.nestedAndInnerInJava

class Outer {
    inner class Inner
    class Nested
}
// is basically equivalent to the Java declaration:

/*
 public class Outer {

     public class Inner {

     }

     public static class Nested {

     }

 }
*/