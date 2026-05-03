package ru.saytikus.androidsimpleclient.data.chatList.dto

import kotlinx.serialization.Serializable
import ru.saytikus.androidsimpleclient.data.core.message.MessageDto
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@Serializable
data class ChatListItemDto @OptIn(ExperimentalUuidApi::class) constructor(
    val chatId: Uuid,

    val type: String,

    val title: String?,

    val companionName: String?,

    val lastMessage: MessageDto?,

    val unreadCount: Int,

    val lastActivityAt: String
)
