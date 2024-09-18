package de.neofonie.messagingmodulekmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform