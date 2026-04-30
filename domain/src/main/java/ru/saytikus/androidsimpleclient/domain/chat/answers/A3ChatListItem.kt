package ru.saytikus.androidsimpleclient.domain.chat.answers

import ru.saytikus.androidsimpleclient.domain.common.message.Message

data class A3ChatListItem(
    val chatId: String,

    val type: String,

    val title: String,

    val companionName: String,

    val lastMessage: Message,

    val unreadCount: Int,

    val lastActivityAt: String
)