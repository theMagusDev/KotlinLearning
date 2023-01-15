package hyperskill.scopeFunctions

// run() //

// When we use run, it sounds like:
// "Do some work with a certain object"

/* Features:

* Is an extension function.
* Context object is available as 'this'.
* Returns the result of a lambda.

run() does the same as with() but invokes
as let() - as an extension function of the context object.

Use when:

 */

/*
    1) First, when we want to initialize a new object and pass the result
    of a lambda to it. It is important — our new object is independent and
    valuable, unlike in the case of the with() function.
 */

class MultiportService(var url: String, var port: Int) {
    fun prepareRequest(): String = "Default request"
    fun query(request: String): String = "Result for query '$request'"
}

fun program12() {
    val service = MultiportService("https://example.kotlinlang.org", 80)

    val result = service.run {
        port = 8080
        this.query(prepareRequest() + " to port $port")
    }

    // the same code written with let() function:
    val letResult = service.let {
        it.port = 8080
        it.query(it.prepareRequest() + " to port ${it.port}")
    }
    // But it is considered to use 'this' when we operate with the object's values

    // the same code written with with() function:
    val withResult = with(service) {
        port = 8080
        query(prepareRequest() + "to port $port")
    }
}

/*
    2) Second, when we want to use a function without an extension and
    execute a block of several operators.
*/

fun program13() {
    val hexNumberRegex = run {
        val digits = "0-9"
        val hexDigits = "A-Fa-f"
        val sign = "+-"

        Regex("[$sign]?[$digits$hexDigits]+")
    }

    for (match in hexNumberRegex.findAll("+1234 -FFFF not-a-number")) {
        println(match.value)
    }
}

/* main() function */

fun main() {
    program12()
    program13()
}


