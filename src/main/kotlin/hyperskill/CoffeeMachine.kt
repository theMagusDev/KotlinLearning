package hyperskill

fun main() {
    println("Write how many ml of water the coffee machine has:")
    val water = readln().toInt()
    println("Write how many ml of milk the coffee machine has:")
    val milk = readln().toInt()
    println("Write how many grams of coffee beans the coffee machine has:")
    val coffeeBeans = readln().toInt()

    println("Write how many cups of coffee you will need:")
    val number = readln().toInt()

    val capability = minOf(water / 200, milk / 50, coffeeBeans / 15)

    if (capability > number) {
        println("Yes, I can make that amount of coffee (and even ${capability - number} more than that)")
    } else if (capability == number) {
        println("Yes, I can make that amount of coffee")
    } else {
        println("No, I can make only ${capability} cups of coffee")
    }
}