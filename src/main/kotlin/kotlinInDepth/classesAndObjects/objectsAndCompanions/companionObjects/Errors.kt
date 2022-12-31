package kotlinInDepth.objectsAndCompanions.companionObjects

// It’s also an error to use the companion modifier for a
// top-level object or an object nested into another object.

// companion object {} // Modifier 'companion' is not applicable inside 'file'

object topLevelObject {
    // companion object {} // Modifier 'companion' is not applicable inside 'standalone object'
}

// In the first case, you lack a class definition
// to bind the companion to, while in the second
// companion is basically redundant.

// Also two companion objects for one class are forbidden.

// Companion objects in Kotlin may be considered a counterpart of
// Java’s static context. Like statics, companion members share
//the same global state and can access any member of an enclosing class
//regardless of its visibility. The crucial difference, however,
// is that their global state is an object instance. This gives
// much more flexibility than Java’s statics as companion objects
// may have supertypes and passed around like any other object.