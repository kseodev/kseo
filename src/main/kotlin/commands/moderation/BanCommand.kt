package com.github.kseodev.commands.moderation

import com.github.kseodev.utils.AnswerUtil
import com.github.kseodev.utils.ColorUtil
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import net.dv8tion.jda.api.interactions.commands.OptionType
import com.jagrosh.jdautilities.command.SlashCommandEvent
import com.jagrosh.jdautilities.command.SlashCommand
import dev.minn.jda.ktx.messages.Embed
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.UserSnowflake
import java.time.Instant
import java.util.concurrent.TimeUnit

class BanCommand : SlashCommand() {
    init {
        name = "ban"
        help = "Заблокировать пользователя."

        userPermissions = arrayOf(Permission.BAN_MEMBERS)
        guildOnly = true

        options = listOf(
            OptionData(OptionType.USER, "user", "Пользователь, которого нужно заблокировать.", true, false),
            OptionData(OptionType.STRING, "reason", "Причина для блокировки", false, false)
            // TODO: duration & reason autocomplete
        )
    }

    override fun execute(event: SlashCommandEvent) {
        if (!event.guild?.getMemberById(event.jda.selfUser.id)?.hasPermission(Permission.BAN_MEMBERS)!!) {
            return AnswerUtil.error(event, "У меня нет права блокировать пользователей.").setEphemeral(true).queue()
        }
        if (event.guild?.retrieveBan(UserSnowflake.fromId(event.optUser("user")!!.id)) != null) {
            return AnswerUtil.error(event, "Пользователь уже заблокирован на этом сервере.").setEphemeral(true).queue()
        }
        if (!event.guild?.getMemberById(event.jda.selfUser.id)?.canInteract(event.guild?.getMemberById(event.optUser("user")!!.id)!!)!!) { // eto pizdec ! ? ? ? !! !! !!
            return AnswerUtil.error(event, "Я не могу взаимодействовать с этим пользователем.").setEphemeral(true).queue()
        }
        try {
            event.guild?.ban(UserSnowflake.fromId(event.optUser("user")!!.id), 9999, TimeUnit.DAYS)
                ?.reason(
                    event.optString("reason", "Не указано.")
                )
                ?.queue()

            event.replyEmbeds(Embed {
                title = "Успешно!"
                color = ColorUtil.GREEN
                description = "Пользователь ${event.optUser("user")!!.asMention} успешно заблокирован!"

                timestamp = Instant.now()
            }).setEphemeral(true).queue()


        }
    }
}