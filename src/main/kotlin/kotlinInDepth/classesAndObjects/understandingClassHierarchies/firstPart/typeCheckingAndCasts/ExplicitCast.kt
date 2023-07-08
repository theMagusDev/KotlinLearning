package kotlinInDepth.classesAndObjects.understandingClassHierarchies.firstPart.typeCheckingAndCasts

// Kotlin supports two operators of this kind: 'as' and its safe version 'as?'.
// The difference lies in their treatment of values which do not conform
// to the target type: while 'as' throws an exception, 'as?' simply returns null:

val o: Any = 123
fun program4() {
    println((o as Int) + 1) // 124
    println((o as? Int)!! + 1) // 124
    println((o as? String ?: "").length) // 0
    println((o as String).length) // Exception
}

// as VS as?
fun program5() {
    println(o as? String) // null
    println(o as String) // Exception
}
