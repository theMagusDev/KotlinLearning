package kotlinInDepth.functions

fun main(args: Array<String>) {

    /*
    The top-level main() function is in fact a static member
    of a special facade class generated per Kotlin file. (DecompileTopLevelAndLocalFunsKT.class)
     */
    class AutoCreatedLocalClass {

        fun localFunction() {

            /*
         For the local functions, the Kotlin compiler performs a similar trick
         which involves a declaration of a special class
         (you can compare it with a local class in Java),
         which contains the local function as its member and captures
         its context-like variables and parameters of the enclosing function.

         Note that this implies some performance overhead
         as your program may need to create a new instance
         of such a class on every call of the local function.
         */
        }
    }

    val autoCreatedLocalClass = AutoCreatedLocalClass()
    autoCreatedLocalClass.localFunction()

    // This is what Kotlin compiler does every time you call the local function.
}