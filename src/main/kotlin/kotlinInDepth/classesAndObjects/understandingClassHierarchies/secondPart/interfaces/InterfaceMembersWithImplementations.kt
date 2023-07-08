package kotlinInDepth.classesAndObjects.understandingClassHierarchies.secondPart.interfaces

// File #2
interface Transport {
    val currentSpeed: Int
    open val isMoving // Redundant modality modifier
        get() = currentSpeed != 0
    fun move()
    fun stop()
    fun report() {
        println(if (isMoving) "Moving at $currentSpeed" else "Still")
    }
    // These implementations are considered implicitly open and thus
    // can be overridden by inheritors.

    // Marking interface member as final is a compilation error:
//  final fun sayHello() = "Hello, world!" // Error: Modifier 'final' is not applicable inside 'interface'
}

// You may, however, use extension functions and properties
fun Vehicle.compareSpeed(vehicle: Vehicle): Int {
    return currentSpeed - vehicle.currentSpeed
}

// Similarly to classes, interface methods can be overridden
// by inheriting interfaces:

interface Flyable : Transport {
    override fun move() {
        println("I'm flying")
    }
}

// The interface itself is also implicitly abstract. Unlike abstract classes,
// however, interfaces are forbidden to define any constructors.
