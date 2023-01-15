package kotlinInDepth.functionalProgramming.extension.scopeFunctions

// The run() function is an extension which accepts an extension lambda and
// returns its result. The basic use pattern is a configuration of an
// object state followed by a computation of a result value:

class Address {
    var zipCode: Int = 0
    var city: String = ""
    var street: String = ""
    var house: String = ""
    fun post(message: String): Boolean {
        "Message for $zipCode, $city, $street, $house: $message"
        return readlnOrNull()?.uppercase() == "OK"
    }
}

fun program1() {
    val isReceived: Boolean = Address().run {
        // Address instance is available as this
        this.zipCode = 123456
        city = "London"
        street = "Baker Street"
        house = "221b"
        post("Hello!") // return value
    }
}