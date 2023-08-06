package kotlinInDepth.functionalProgramming.scopeFunctions

// apply //

/* Features:

* Is an extension function.
* Context object is available as 'this'.
* Returns the context object.

 */

// apply() is commonly used for object SETTING – for example, if you want to assign new values to class methods or parameters.

// It sounds like "Hey, do apply these settings to this object and its parameters!".

data class Musician1(var name: String, var instrument: String = "Guitar", var band: String = "Radiohead")

fun program1() {
    Musician1("Jonny Greenwood").apply {
        println(this) // Musician1(name=Jonny Greenwood, instrument=Guitar, band=Radiohead)
        this.instrument = "Harmonica"
        band = "Pavement"
        println(this) // Musician1(name=Jonny Greenwood, instrument=Harmonica, band=Pavement)

        // Remember that apply returns the context object. It means that
        // we can pass our object further down the chain and do
        // something else with it.
    }.copy(name = "Thom York")
}

// also //

/* Here are two major characteristics of the also() function:
* 1) The context object is available as it.
* 2) The function returns the context object.
 */

// The usage of also is similar to that of apply, but it's recommended
// to choose also when you work with the entire object and
// don't care about its parameters or methods.

fun program2() {
    val instruments = mutableListOf("Guitar", "Harmonica", "Bass guitar")

    instruments
        .also { println("Right now I can play these instruments: $it") }
        .add("Theremin")
    // Right now I can play these instruments: [Guitar, Harmonica, Bass guitar]

    println("Right now I can play these instruments: $instruments")
    // Right now I can play these instruments: [Guitar, Harmonica, Bass guitar, Theremin]

    // Note:
    var a = 10
    print(a)
    var b = 5
    a = b.also { b = 7 }
    println("a = $a, b = $b") // Output: a = 5, b = 7
    /* Java bytecode:
      int a = 10;
      int b = 5;
      byte varB = b; // from the line #62: 'a = b'
      b = 7; // from the line #62: '.also { b = 7 }'
      System.out.println("a = " + varB + ", b = " + b);
     */
}

fun main() {
    program1()
    program2()
}