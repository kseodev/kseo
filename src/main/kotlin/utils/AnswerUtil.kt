package com.github.kseodev.utils

import com.jagrosh.jdautilities.command.SlashCommandEvent
import dev.minn.jda.ktx.messages.Embed
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction
import java.time.Instant

class AnswerUtil {
    companion object {
        fun error(event: SlashCommandEvent, reasonRaw: String): ReplyCallbackAction {
            val reason = """```
                |• $reasonRaw
                |```
            """.trimMargin()
            return event.replyEmbeds(Embed {
                title = "Произошла ошибка!"
                color = ColorUtil.RED
                description = reason

                timestamp = Instant.now()
            })
        }

        fun successEmbed() : MessageEmbed {
            return Embed {
                title = "Успешно!"
                color = ColorUtil.GREEN

                timestamp = Instant.now()
            }
        }
    }
}