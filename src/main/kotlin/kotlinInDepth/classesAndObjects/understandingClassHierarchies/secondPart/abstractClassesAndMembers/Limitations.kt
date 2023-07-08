package kotlinInDepth.classesAndObjects.understandingClassHierarchies.secondPart.abstractClassesAndMembers

// Abstract classes have some limitations:

abstract class Planet {
    /*
     1) Both abstract properties and functions must explicitly specify their
    return type since it can’t be inferred automatically.
    */
//  abstract val water // Error: This property must have a type annotation

    /*
     2) Abstract properties may not have initializers,
    explicit accessors, or by clauses.
    */

//  abstract val water: Double = 5.0 // Error: Property with initializer cannot be abstract
//  abstract val water: Double by lazy { 3.0 } // Error: Delegated property cannot be abstract

    /*
     3) Abstract functions may not have a body.
    */
//  abstract fun sayHello() = "I'm planet!" // Error: A function 'sayHello' with body cannot be abstract
}
