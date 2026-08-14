package ru.saytikus.androidsimpleclient.domain.chat.dto

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class ChangeTypingCommand(
    val chatId: Uuid,

    val isTyping: Boolean
)
