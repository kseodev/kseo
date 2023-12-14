package com.github.kseodev.commands.utilities

import com.github.kseodev.core.Kseo
import com.jagrosh.jdautilities.command.SlashCommand
import com.jagrosh.jdautilities.command.SlashCommandEvent

class TestCommand : SlashCommand() {
    init {
        name = "getcache"
        help = "Get cached messages"

        guildOnly = true
    }

    override fun execute(event: SlashCommandEvent) {
        val stringBuilder = StringBuilder()
        stringBuilder.append("```\n")
        Kseo.cacheManager.getAllCachedMessages().forEach {
            stringBuilder.append(it.key.toString() + " || " + it.value.authorId + "|*|" + it.value.content + "\n")
        }
        stringBuilder.append("\n```")

        event.reply(stringBuilder.toString()).setEphemeral(true).queue()
    }
}