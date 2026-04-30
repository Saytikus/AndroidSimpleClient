package ru.saytikus.androidsimpleclient.data.chatList.dto

import kotlinx.serialization.Serializable
import ru.saytikus.androidsimpleclient.data.core.message.MessageDto


@Serializable
data class ChatListItemDto(
    val chatId: String,

    val type: String,

    val title: String,

    val companionName: String,

    val lastMessage: MessageDto,

    val unreadCount: Int,

    val lastActivityAt: String
)
