package kotlinInDepth.reflection.kParameter

// Read after callables.kFunction.Example

/*
The KParameter interface contains information about the
function/constructor parameter or receiver(s) of a member/extension
declaration (see 'KParameter API.jpg' for more details)

 'isOptional' property returns true when the parameter has a default value;
 'kind' property indicates whether KParameter corresponds to the
ordinary value, or dispatch/extension receiver. It can return one of the
constants defined in the KParameter.Kind enum:
 1) INSTANCE: This is the dispatch receiver of the member declaration;
 2) EXTENSION_RECEIVER: This is the extension receiver of the extension
declaration;
 3) VALUE: This is an ordinary parameter

KCallable also defines the call() member which allows
you to dynamically invoke the backing callable. In the case
of a function-based callable, the call() invokes the
function itself; if the callable corresponds to a property, the getter
is used instead.
 */

class Person(val name: String, val surname: String)

fun main() {
    val person = Person("John", "Doe")
    val surname = person::class.members.first( predicate = { it.name == "surname" } )
    println(surname.call(person)) // Doe
}

// Next: reflection.kProperty

