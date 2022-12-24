package functions

import java.util.Date

fun volume(length: Int, width: Int, height: Int, ) = length * width * height
val numbers = intArrayOf(1, 2, 3, 4, 5, )

// They are useful when we have multi-line arguments
data class PersonWithTrailingComma(
    val name: String,
    val age: Int,
    val gender:String,
    val nationality: String,
    val address: String,
    val dateOfBirth: Date,
)

// The presence of a trailing comma simplifies swapping individual lines
// since you don’t have to add/remove commas by hand.

// We only focus on the arguments we need to add or change
// without thinking about which one is the last argument
// and which trailing commas we should add or remove.

// Note: the trailing comma is considered incorrect if it’s not preceded by a list element

// val names = arrayOf<String>(,) // Error
// val names2 = arrayOf<String>() // No error, just an empty array
// val numbers2 = intArrayOf(1, 2, 3, ,) // Error

/*
* This syntax is in fact supported for any list of comma-separated syntactic
* elements such as parameters of a class constructor, variables inside a
* destructuring declarations, or enum entries.
 */
