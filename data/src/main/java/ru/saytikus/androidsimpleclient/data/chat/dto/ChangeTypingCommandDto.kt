package ru.saytikus.androidsimpleclient.data.chat.dto

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
@OptIn(ExperimentalUuidApi::class)
data class ChangeTypingCommandDto(
    val chatId: Uuid,

    val isTyping: Boolean
)
