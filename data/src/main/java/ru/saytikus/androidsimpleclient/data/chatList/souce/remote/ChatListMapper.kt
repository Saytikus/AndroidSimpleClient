package ru.saytikus.androidsimpleclient.data.chatList.souce.remote

import ru.saytikus.androidsimpleclient.data.chatList.dto.ChatListItemDto
import ru.saytikus.androidsimpleclient.data.core.message.toDomain
import ru.saytikus.androidsimpleclient.domain.chatList.ChatListItem


fun ChatListItemDto.toDomain() =
    ChatListItem(
        chatId,

        type,

        title,

        companionName,

        lastMessage.toDomain(),

        unreadCount,

        lastActivityAt
    )