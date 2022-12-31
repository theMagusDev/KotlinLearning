package practice.lambdas

fun aggregate(array: IntArray, action: (Int) -> Int): IntArray {
    val resultArray = array.copyOf()
    for (i in array.indices) {
        array[i] = action.invoke(array[i])
    }
    return array
}

fun program1() {
    val myArray = intArrayOf(1, 2, 3)
    println(aggregate(myArray, { element -> element * 2}).contentToString()) // 2, 4, 6
    println(aggregate(myArray, {it * it}).contentToString()) // 4, 16, 36
    println(aggregate(myArray, { element -> element - 1}).contentToString()) // 3, 15, 35
}

fun compareNumbers(a: Int, b: Int, check: (Int, Int) -> Boolean): Boolean {
    return check(a, b)
}

fun program2() {
    println(compareNumbers(3, 5) { first, second -> first == second}) // false
    compareNumbers(4, 4) {first, second -> first == second} // true
}

fun aggregateArray(array: IntArray, operation: (Int) -> Int): IntArray {
    for (i in array.indices) {
        array[i] = operation(array[i])
    }
    return array
}

fun program3() {
    // Divide % 2 == 0 elements on 2 and make % 2 == 1 elements equal to 0
    println(aggregateArray(intArrayOf(11, 12, 16), { element -> if (element % 2 == 0) element / 2 else 0 }).contentToString()) // 0, 6, 8
}

fun measureExecutionTime(action: () -> Unit): Long {
    val start: Long = System.nanoTime()
    action()
    return System.nanoTime() - start
}

fun program4() {
    println("print() command executes ${measureExecutionTime { print("") }} nanoseconds.")
    println("Thread.sleep(1000) command executes ${measureExecutionTime { Thread.sleep(1000) }} nanoseconds.")
}

fun program5() {
    val inc: (Int) -> Int = {it + 1}
    // or
    val inc1: (Int) -> Int = {x -> x + 1}
    // or
    val inc2 = {x: Int -> x * 2}

    println(inc(1)) // 2

    val lessThan: (Int, Int) -> Boolean = {a, b -> a < b}
    println(lessThan.invoke(3, 5)) // true. Same as lessThan(3, 5)
    println(lessThan.invoke(5, 3)) // false
}

fun program6() {
    val shifter: (Int) -> ((Int) -> Int) = {number -> {i -> number + i}}
    val inc: (Int) -> Int = shifter(1) // shifter, receive 1
    println(inc(5)) // inc, receive 5
    // Output: 6

    val executeOnZero: ((Int) -> Int) -> Int = {function -> function(0)}
    println(executeOnZero({it + 2})) // 2
    println(executeOnZero({zero -> zero * 2})) // 0
}

fun realWorldUsage() {
    val originalText = "I don't know... what to say..."
    println(originalText.filter { char -> char != '.' }) // I don't know what to say
}

fun main() {
    program1()
    program2()
    program3()
    program4()
    program5()
    program6()
    realWorldUsage()
}