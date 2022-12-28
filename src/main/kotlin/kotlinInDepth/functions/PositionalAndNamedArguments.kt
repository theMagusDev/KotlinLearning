package kotlinInDepth.functions

fun rectangleArea(width: Double, height: Double): Double {
    return width*height
}

fun main() {
    val w = 5.0
    val h = 4.0
    println("Rectangle area: ${rectangleArea(w, h)}")

    // named arguments
    println("Rectangle area: ${rectangleArea(width = w, height = h)}")
    println("Rectangle area: ${rectangleArea(height = h, width = w)}") // arguments order does not matter

    fun swap(s: String, from: Int, to: Int): String {
        val chars = s.toCharArray()
        // Swap array elements:
        val temp = chars[from]
        chars[from] = chars[to]
        chars[to] = temp
        return chars.toString()
    }

    println(swap("Hello", 1, 2)) // Hlelo
    println(swap("Hello", from = 1, to = 2)) // Hlelo
    println(swap("Hello", to = 3, from = 2)) // lelHo
    println(swap("Hello", 1, to = 2)) // Hlleo
    println(swap(from = 1, s = "Hello", to = 2)) // Hlelo
    println(swap(s = "Hello", 1, 2))
    println(swap(s = "Hello", 1, to = 2))
    // println(swap(1, s = "Hello", 2)) // Compilation error
    // println(swap(1, "Hello", to = 2)) // Compilation error
    fun format(num: Int, radix: Int, pad: Int, prefix: String):
    String {
        var s = num.toString(radix)
        if (pad > 0 && s.length < pad) {
            s = "0".repeat(pad - s.length) + s
        }
        return prefix + s
    }

    println(format(255, 16, 8, "0x"))
    println(format(13, 2, 8, "0b"))
    /*
    * If you look at a particular call the main() body, you’ll likely guess that the
    * first argument is a number to be formatted while the last one is a prefix but
    * still might mistake radix for pad and vice versa. Using 1.4 argument
    * mixing, you can accompany them with explicit names thus improving your
    * code readability:
     */
    println(format(255, radix = 16, pad = 8, "0x"))
    println(format(13, radix = 2, pad = 8, "0b"))

}