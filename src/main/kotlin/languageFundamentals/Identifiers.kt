package languageFundamentals

fun main() {

    /* Identifiers */
    val import = 5 // you are allowed to use Soft keywords, but not Hard.
    val привет = 10 // OK, but is not a good practise

    /* Quoted identifiers */
    val `fun` = 123 // OK, but is not a good practise
    val `name with spaces` = 321
    println(`fun`) // 123
    println(`name with spaces`) // 321

    var a = 5
    // a = "String" // error
}