package com.github.kseodev.events

import com.github.kseodev.core.Kseo
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class MessageToCache : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        Kseo.cacheManager.cacheMessage(event.message)
    }
}