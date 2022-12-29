package practice

import java.util.*

private const val LIVING_WAGE = 14883

class Office (vararg employee: Employee) {
    var workersAmount = employee.size
}

class Employee() {

    companion object {
        var counter: Int = 0

        fun printInfo(employee: Employee) {
            println("""
            === Employee info ===
            ID: ${employee.id}
            name: ${employee.name}
            surname: ${employee.surname}
            age: ${employee.age}
            department: ${employee.department}
            salary: ${employee.salary}
        """.trimIndent())
        }

    }

    val id: Int = ++counter
    var name: String? = null
    var surname: String? = null
    var age: Int? = null
    var department: String? = null
    var salary: Int = LIVING_WAGE

    init {
        println("Employee object created.")
    }

    constructor(name: String, surname: String, age: Int) : this() {
        this.name = name
        this.surname = surname
        this.age = age
    }
    constructor(name: String, surname: String, age: Int, salary: Int) : this(name, surname, age) {
        this.salary = salary
    }
    constructor(name: String, surname: String, age: Int, department: String) : this(name, surname, age) {
        this.department = department
        this.salary = when (department.lowercase(Locale.getDefault())) {
            "it" -> 55000
            "cleaning" -> 21000
            "chief" -> 75000
            else -> LIVING_WAGE
        }
    }
    constructor(name: String, surname: String, age: Int, department: String, salary: Int) : this(name, surname, age, salary) {
        this.department = department
    }

    fun sayHello() {
        println("Hello from worker ${name ?: "No_name"} ${surname ?: "No_surname"}! I am working in ${department ?: "Unknown"} department, and I'm earning $salary₽ per month.")
    }

}

fun main() {
    val employees: Array<Employee> = arrayOf(
        Employee(),
        Employee("Ivan", "Gustov", 23),
        Employee("Yuriy", "Magus", 17, 150000),
        Employee("Anastasia", "Morozova", 21, "IT"),
        Employee("Yaroslav", "Tihonov", 19, "Chief", 100000),
    )
    for (employee in employees) {
        Employee.printInfo(employee)
    }

    val employee1 = Employee()
    val employee2 = Employee("Ivan", "Gustov", 23)
    val employee3 = Employee("Yuriy", "Magus", 17, 150000)
    val employee4 = Employee("Anastasia", "Morozova", 21, "IT")
    val employee5 = Employee("Yaroslav", "Timonov", 19, "Chief", 100000)
    val office = Office(employee1, employee2, employee3, employee4, employee5)
    employee1.sayHello()
    // Hello from worker No_name No_surname! I am working in Unknown department, and I'm earning 14883₽ per month.
    employee2.sayHello()
    // Hello from worker Ivan Gustov! I am working in Unknown department, and I'm earning 14883₽ per month.
    employee3.sayHello()
    // Hello from worker Yuriy Magus! I am working in Unknown department, and I'm earning 150000₽ per month.
    employee4.sayHello()
    // Hello from worker Anastasia Morozova! I am working in IT department, and I'm earning 55000₽ per month.
    employee5.sayHello()
    // Hello from worker Yaroslav Timonov! I am working in Chief department, and I'm earning 100000₽ per month.

}