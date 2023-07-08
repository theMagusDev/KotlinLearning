package kotlinInDepth.classesAndObjects.understandingClassHierarchies.secondPart.interfaces

// File #3

// Since interfaces are not allowed to define the state, they can’t contain
// properties with backing fields. In particular, properties with initializers and
// delegates are forbidden.

/*
interface Boat {
    val currentSpeed: Int = 0 // Error: Property initializers are not allowed in interfaces
    val maxSpeed: Int by lazy { 100 } // Error: Delegated properties are not allowed in interfaces
}
 */


