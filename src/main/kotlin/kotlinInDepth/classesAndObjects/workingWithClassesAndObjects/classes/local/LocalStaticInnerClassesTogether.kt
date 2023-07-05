package kotlinInDepth.classesAndObjects.workingWithClassesAndObjects.classes.local

// Local classes may contain all the members permitted in any other classes
// such as functions, properties, constructors, or nested classes.
// Note, however, that their nested classes must always be marked as inner:
fun main(args: Array<String>) {

    class Local {
        val length = args.size

        inner class Inner {
            val firstArg = args.firstOrNull()
        }
    }
}