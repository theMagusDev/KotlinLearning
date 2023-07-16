package kotlinInDepth.annotations.define

/*
Annotations offer a way of providing instructions and metadata
(data about data) to the compiler, IDE, or framework to handle some
complex tasks and make your life easier.
 */

// Annotations will be applied to the closest element as in the example below:

@Suppress("CanBeVal")

var myFirstName = "Alex"           // Suppress annotation will be applied here
var myFriendName = "Aaron"        // will NOT be applied here

// Some built-in annotations like @Throws, @JvmName, or @NotNull are used
// for tuning Java/Kotlin interoperability.
