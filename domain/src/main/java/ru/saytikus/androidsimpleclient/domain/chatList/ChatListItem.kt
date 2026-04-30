package ru.saytikus.androidsimpleclient.domain.chatList

import ru.saytikus.androidsimpleclient.domain.common.message.Message

data class ChatListItem(
    val chatId: String,

    val type: String,

    val title: String,

    val companionName: String,

    val lastMessage: Message,

    val unreadCount: Int,

    val lastActivityAt: String
)