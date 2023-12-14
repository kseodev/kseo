package com.github.kseodev.core

import com.github.kseodev.commands.utilities.TestCommand
import com.github.kseodev.events.MessageToCache
import com.jagrosh.jdautilities.command.CommandClientBuilder
import io.github.cdimascio.dotenv.dotenv
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.cache.CacheFlag

class Kseo {
    private val dotEnv = dotenv()

    private val token = dotEnv.get("DISCORD_TOKEN")
    private val ownerId = dotEnv.get("OWNER_ID")

    init {

        val commandClientBuilder = CommandClientBuilder()
            .setOwnerId(ownerId)
            .useHelpBuilder(false)
            .setActivity(Activity.customStatus("В разработке..."))
            .setStatus(OnlineStatus.IDLE)

        commandClientBuilder.addSlashCommands(TestCommand())

        JDABuilder.create(token, GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
            .disableCache(CacheFlag.CLIENT_STATUS, CacheFlag.ONLINE_STATUS, CacheFlag.ACTIVITY)
            .addEventListeners(commandClientBuilder.build())

            .addEventListeners(MessageToCache())

            .build()

        Message.suppressContentIntentWarning()
    }

    companion object {
        val cacheManager = CacheManager()
    }
}