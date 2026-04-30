package ru.saytikus.androidsimpleclient.data.chat.dto

import kotlinx.serialization.Serializable
import ru.saytikus.androidsimpleclient.data.core.message.MessageDto
import ru.saytikus.androidsimpleclient.domain.common.message.Message


@Serializable
data class A3ChatListItemDto(
    val chatId: String,

    val type: String,

    val title: String,

    val companionName: String,

    val lastMessage: MessageDto,

    val unreadCount: Int,

    val lastActivityAt: String
)
