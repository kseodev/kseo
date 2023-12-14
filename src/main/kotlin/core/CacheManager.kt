package com.github.kseodev.core

import io.github.reactivecircus.cache4k.Cache
import net.dv8tion.jda.api.entities.Message

class CacheManager {
    private val cachedMessagesLimit = 1000L

    private val cachedMessages = Cache.Builder<String, CachedMessage>()
        .maximumCacheSize(cachedMessagesLimit)
        .build()

    fun cacheMessage(message: Message) {
        cachedMessages.put(message.id, CachedMessage(message.author.id, message.contentDisplay))
    }

    fun getCachedMessage(messageId: String): CachedMessage? {
        return cachedMessages.get(messageId)
    }

    fun getAllCachedMessages(): Map<in String, CachedMessage> {
        return cachedMessages.asMap()
    }
}

class CachedMessage(val authorId: String, val content: String)