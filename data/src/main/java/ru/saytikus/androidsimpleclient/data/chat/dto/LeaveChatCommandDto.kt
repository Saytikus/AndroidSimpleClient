package ru.saytikus.androidsimpleclient.data.chat.dto

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Serializable
@JvmInline
value class LeaveChatCommandDto(
    val chatId: Uuid
)