package kotlinInDepth.basicStatements.nestedLoopsAndLabels

// without labels and break
fun day1() {
    for (hour in 0..23) {
        for (minute in 0..59) {
            println("$hour:$minute")
        }
    }
}

// break
fun day2() {
    hours@ for (hour in 0..23) {
        minutes@ for (minute in 0..59) {
            if (hour == 12) {
                break@hours
            }
            println("$hour:$minute")
        }
    }
}

// continue
fun day3() {
    for (hour in 0..23) {
        for (minute in 0..59) {
            if (minute % 15 != 0) {
                continue
            }
            println("$hour:$minute")
        }
    }
}

fun main() {
    println("=== Day #1 ===")
    day1()
    println("=== Day #2 ===")
    day2()
    println("=== Day #3 ===")
    day3()
}