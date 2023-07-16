package kotlinInDepth.reflection.classifiers.kTypeParameter

// Read after kClass Example

// Another classifier variety is represented by the KTypeParameter interface.
// See 'KTypeParameter API.jpg' for more details.

// The upperBounds give you a list of upper bound types similar to the
// supertypes property of KClass.
interface MyMap<K: Any, out V>
fun program10() {
    val typeParameters = MyMap::class.typeParameters
    println(typeParameters.joinToString { "${it.name} : ${it.upperBounds}" })
    // K : [kotlin.Any], V : [kotlin.Any?]
}

// The variance property returns a constant of the KVariance enum
// with constants INVARIANT, IN, OUT


fun main() {
    program10()
}
















