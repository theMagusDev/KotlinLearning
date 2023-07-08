package kotlinInDepth.classesAndObjects.understandingClassHierarchies.firstPart.subclass

// It’s worth pointing out an important difference between members and
// extensions. While class members can be overridden and thus chosen
// in the runtime, extensions are always resolved statically.

// In other words, when calling an extension, compiler always chooses it
// on the base of a receiver type, which is the function's first argument.

open class Employee {
    open fun work() {
        println("I'm working")
    }
}
fun Employee.sleep() {
    println("I'm going to sleep after hard work")
}
class Teacher : Employee() {
    override fun work() {
        println("I'm teaching children")
    }
}
fun Teacher.sleep() {
    println("I'm going to sleep after teaching children")
}

fun program4() {
    val employee = Employee()
    employee.work() // I'm working
    employee.sleep() // I'm going to sleep after hard work
    // calling 'public static void sleep(Employee employee)'

    val teacher = Teacher()
    teacher.work() // I'm teaching children
    teacher.sleep() // I'm going to sleep after teaching children
    // calling 'public static void sleep(Teacher teacher)'
}

// Properties can be overridden too.

open class Internet(var visitorsPerDay: Int) {
    open val adPrice: Int
        get() = (visitorsPerDay * 0.01).toInt()
}
class SocialNetwork(visitorsPerDay: Int) : Internet(visitorsPerDay) {
    override val adPrice: Int
        get() = (visitorsPerDay * 0.3).toInt()
}

fun program5() {
    val usualWebsite = Internet(visitorsPerDay = 500)
    println(usualWebsite.adPrice) // 5

    val superMessenger = SocialNetwork(visitorsPerDay = 350)
    println(superMessenger.adPrice) // 105
}

fun main() {
    program4()
    program5()
}