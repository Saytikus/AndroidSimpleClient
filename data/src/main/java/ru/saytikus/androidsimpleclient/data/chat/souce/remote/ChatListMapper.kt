package ru.saytikus.androidsimpleclient.data.chat.souce.remote

import ru.saytikus.androidsimpleclient.data.chat.dto.ChatListItemDto
import ru.saytikus.androidsimpleclient.data.core.message.toDomain
import ru.saytikus.androidsimpleclient.domain.chat.entities.ChatListItem
import kotlin.uuid.ExperimentalUuidApi


@OptIn(ExperimentalUuidApi::class)
fun ChatListItemDto.toDomain() =
    ChatListItem(
        chatId,

        type,

        title,

        companionName,

        lastMessage?.toDomain(),

        unreadCount,

        lastActivityAt
    )