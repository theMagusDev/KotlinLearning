package kotlinInDepth.classes.local

// Similarly to Java, Kotlin classes
// can be declared inside the function body.
fun main() {
    var secret = 123

    class Point(val x: Int, val y: Int) {
        fun shift(dx: Int, dy: Int): Point = Point(x + dx, y + dy)
        override fun toString() = "($x, $y)"
        init {
            println("main() fun's secret increment: $secret -> ${++secret}")
            // local classes can access declarations
            // from the enclosing code and even modify them.
        }
    }

    val p = Point(10, 10)
    println(p.shift(-1, 3)) // (9, 13)

}

fun abc() {
    // println(Point(0, 0)) // Error: can’t resolve Point
}

/*
Unlike Kotlin, Java doesn’t allow modification of captured
variables. Moreover, all such variables must be explicitly marked as final
when used inside the anonymous class. Note, however, that the ability to
change captured variables in Kotlin comes with a certain price. In order to
share variables between the anonymous object and its enclosing code, the
Kotlin compiler boxes their values inside special wrapper objects.
 */

/* Kotlin:

fun main() {
    var x = 1
    class Counter {
        fun increment() {
            x++
        }
    }
    Counter().increment()
    println(x) // 2
}

 */

/* Java:

import kotlin.jvm.internal.Ref.IntRef;

class MainKt {
    public static void main(String[] args) {
        final IntRef x = new IntRef(); // create wrapper
        x.element = 1;

        final class Counter {

            public final void increment() {
                x.element++; // modify shared data
            }
        }

        (new Counter()).increment();
        System.out.println(x.element); // read shared data
    }
}
 */

// Note that immutable variables have no such overhead
// since they do not require any wrappers.