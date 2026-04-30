package ru.saytikus.androidsimpleclient.data.core.message

import ru.saytikus.androidsimpleclient.domain.common.message.Message


fun Message.toDto() =
    MessageDto(
        id,

        senderId,

        senderName,

        text,

        createdAt,

        isRead
    )


fun MessageDto.toDomain() =
    Message(
        id,

        senderId,

        senderName,

        text,

        createdAt,

        isRead
    )