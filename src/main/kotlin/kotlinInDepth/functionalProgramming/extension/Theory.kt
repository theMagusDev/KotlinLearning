package kotlinInDepth.functionalProgramming.extension

// The need to extend an existing class is quite common in practice. As a
// program evolves, a developer may want to add new functions and
// properties to classes thus extending their API. But sometimes, simply
// adding new code to a class is not an option since a class in question may be
// a part of some library and its modification will require significant efforts
// if feasible at all. Putting all possible methods into a single class may
// also be impractical as not all of them are used together and therefore
// worth decoupling into several program units.
// In Java, such extra methods are often packed into utility classes.
// A common example is java.util.Arrays and java.util.Collections classes
// which contain methods extending capabilities of Collection interfaces.
// The problem with such classes is that they often produce unnecessary
// boilerplate. For example, a typical usage of utility methods in Java may
// look like this:

/*
int index = Collections.indexOfSubList(
    Arrays.asList(“b”, “c”, “a”),
    Arrays.asList(“a”, “b”)
)
 */

// Apart from clattering the source code, such calls do not allow you
// to make use of autocompletion available for class members in major IDEs
// such as IntelliJ and Eclipse.

// That’s the primary motivation behind Kotlin extensions which allow you to
// use functions and properties defined outside a class as if they were its
// members. Supporting the “open/closed” design principle, they allow you to
// extend existing classes without modifying them.
