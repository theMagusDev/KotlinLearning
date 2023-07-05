package kotlinInDepth.objectsAndCompanions.objects

import kotlinInDepth.objectsAndCompanions.objects.Application.exit

// You may not, however, import all the object members
// at once using an on-demand import.

// import kotlinInDepth.objectsAndCompanions.objects.Application.* // Error

// The reason behind such a restriction is that object definitions,
// like any other classes, include common methods such as toString()
// or equals() which can be imported too if on-demand was allowed.

fun main() {
    println(Application.name) // using qualifies reference
    exit() // using simple name
}