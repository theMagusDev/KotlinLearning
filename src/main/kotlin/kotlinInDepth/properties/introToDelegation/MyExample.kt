package kotlinInDepth.properties.introToDelegation

interface OS {
    val name: String
    val fileSystem: String
    fun boot()
}

class Linux : OS {
    override val name: String = "Linux"
    override val fileSystem: String = "ext4"
    override fun boot() {
        println("Booting...")
        Thread.sleep(5000)
        println("Boot successful.")
    }
}

class Android(linux: Linux) : OS by linux {
    override val name: String = "Android"
    // Other features are the same, just delegate them to Linux
}