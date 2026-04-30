package ru.saytikus.androidsimpleclient.data.chat.souce.remote

import ru.saytikus.androidsimpleclient.data.chat.dto.A3ChatListItemDto
import ru.saytikus.androidsimpleclient.data.core.message.toDomain
import ru.saytikus.androidsimpleclient.domain.chat.Chat


fun A3ChatListItemDto.toDomain() =
    Chat(
        chatId,

        type,

        title,

        companionName,

        lastMessage.toDomain(),

        unreadCount,

        lastActivityAt
    )