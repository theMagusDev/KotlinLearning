package kotlinInDepth.properties.accessors

import java.util.Date
/*
Property accessors may have their own visibility modifiers. They can be
useful if you, say, want to forbid changing your property outside their
containing class, thus making it effectively immutable for the outside world.
If you don’t need a non-trivial implementation of an accessor, you can
abbreviate it by a single get/set keyword:
 */

class Person4(name: String) {
    var lastChanged: Date? = null
        private set // can not be changed outside Person class
    var name: String = name
        set(value) {
            lastChanged = Date()
            field = value
        }
}

// Note: Private properties, on the other hand, by default have no accessor methods
// generated since they can’t be used outside the containing class or file.
// Access to such properties is optimized to refer to their backing fields
// directly.