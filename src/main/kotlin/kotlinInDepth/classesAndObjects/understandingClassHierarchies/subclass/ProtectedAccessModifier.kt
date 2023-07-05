package kotlinInDepth.classesAndObjects.understandingClassHierarchies.subclass

// Similar to Java, Kotlin has a special access modifier which restricts the
// member scope to its inheritors. Such members are marked with the
// protected keyword.

// Java vs Kotlin: Mind the difference between the protected modifier in
// Kotlin and Java. While both languages permit to access protected members
// from inheritor classes, Java also allows you to use them from any code
// located in the same package. In Kotlin that's forbidden.

open class Staff {
    protected fun startMeeting() {
        println("Staff meeting is starting...")
    }
    open fun requestMeeting() {
        println("Staff meeting was requested")
    }
}
class Director : Staff() {
    override fun requestMeeting() {
        super.requestMeeting()
        startMeeting()
    }
}
fun program6() {
    val director = Director()
    director.requestMeeting() // OK
//  director.startMeeting() // Error. Cannot access 'startMeeting': it is protected in 'Director'
}
