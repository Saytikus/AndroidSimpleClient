package ru.saytikus.androidsimpleclient.domain.chat

import ru.saytikus.androidsimpleclient.domain.common.message.Message

data class Chat(
    val chatId: String,

    val type: String,

    val title: String,

    val companionName: String,

    val lastMessage: Message,

    val unreadCount: Int,

    val lastActivityAt: String
)