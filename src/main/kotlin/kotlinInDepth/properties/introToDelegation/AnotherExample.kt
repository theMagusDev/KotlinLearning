package kotlinInDepth.properties.introToDelegation

interface Software {
    fun getLicense(): String
}

class ProprietarySoftware : Software {
    override fun getLicense() = "Some Proprietary Software License"
}

class FreeSoftware : Software {
    override fun getLicense() = "Some Free Software License"
}

// Now we define a class called WordProcessor
// that implements the Software interface.

class WordProcessor(private val software: Software) : Software {
    override fun getLicense(): String = software.getLicense()
}

fun main() {
    val windows = ProprietarySoftware()
    println(windows.getLicense()) // Some Proprietary Software License

    val linux = FreeSoftware()
    println(linux.getLicense()) // Some Free Software License

    val wordProcessorWin = WordProcessor(windows)
    val wordProcessorLinux = WordProcessor(linux)

    println(wordProcessorWin.getLicense()) // Some Proprietary Software License
    println(wordProcessorLinux.getLicense()) // Some Free Software License
}

// As Kotlin supports class delegation natively,
// let’s implement the previous example in a more elegant way.

class newWordProcessor(software: Software) : Software by software
// no need in overriding getLicense(), this function is delegated
// to 'software' (object, which implements Software interface)