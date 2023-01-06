package kotlinInDepth.functionalProgramming.extension

fun String.second(): Char {
    if (this.isEmpty()) throw NoSuchElementException("Char sequence is empty.")
    if (this.length < 2) throw NoSuchElementException("Char sequence length is less than 2.")
    return this[1]
}

// Let’s take a look at the decompiled version of our function above (Simplified):
/*
public final class UnderTheHoodKt {
    public static final char second(String receiver) {
        CharSequence charSequence = (CharSequence)receiver;
        if (charSequence.length() == 0) {
            throw (Throwable)(new NoSuchElementException("Char sequence is empty."));
        } else if (receiver.length() < 2) {
            throw (Throwable)(new NoSuchElementException("Char sequence length is less than 2."));
        } else {
            return receiver.charAt(1);
        }
    }
}
 */

// So our extension function is just a static function
// defined in UnderTheHoodKt class with an additional parameter
// which represents the receiver expression.

// And it also explains why we can only
// access public methods and properties of the receiver object.

// Referencing an extension function from Java can therefore be possible.
// You just have to know the filename and the method name.
// In our case it can be invoked by
// char second = UnderTheHoodKt.second(12);
