package de.neofonie.messagingmodulekmp

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Test, ${platform.name}!"
    }
}