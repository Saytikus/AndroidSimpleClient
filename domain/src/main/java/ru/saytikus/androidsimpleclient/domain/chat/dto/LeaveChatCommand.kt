package ru.saytikus.androidsimpleclient.domain.chat.dto

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@JvmInline
value class LeaveChatCommand(
    val chatId: Uuid
)