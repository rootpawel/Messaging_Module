package de.neofonie.messagingmodulekmp

expect class ClipboardSaver {
    fun saveToClipboard(text: String)
    fun getText():String
}