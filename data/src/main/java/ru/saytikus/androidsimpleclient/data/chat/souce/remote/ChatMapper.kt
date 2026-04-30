package ru.saytikus.androidsimpleclient.data.chat.souce.remote

import ru.saytikus.androidsimpleclient.data.chat.dto.A3ChatListItemDto
import ru.saytikus.androidsimpleclient.data.core.message.toDomain
import ru.saytikus.androidsimpleclient.domain.chat.answers.A3ChatListItem


fun A3ChatListItemDto.toDomain() =
    A3ChatListItem(
        chatId,

        type,

        title,

        companionName,

        lastMessage.toDomain(),

        unreadCount,

        lastActivityAt
    )