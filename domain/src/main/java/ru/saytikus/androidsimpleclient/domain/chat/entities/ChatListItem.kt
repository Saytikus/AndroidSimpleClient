package ru.saytikus.androidsimpleclient.domain.chat.entities

import ru.saytikus.androidsimpleclient.domain.common.message.Message
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class ChatListItem @OptIn(ExperimentalUuidApi::class) constructor(
    val chatId: Uuid,

    val type: String,

    val title: String?,

    val companionName: String?,

    val lastMessage: Message?,

    val unreadCount: Int,

    val lastActivityAt: String
)