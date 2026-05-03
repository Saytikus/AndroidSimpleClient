package ru.saytikus.androidsimpleclient.data.core.message

import ru.saytikus.androidsimpleclient.domain.common.message.Message
import kotlin.uuid.ExperimentalUuidApi


@OptIn(ExperimentalUuidApi::class)
fun Message.toDto() =
    MessageDto(
        id,

        senderId,

        senderName,

        text,

        createdAt,

        isRead
    )


@OptIn(ExperimentalUuidApi::class)
fun MessageDto.toDomain() =
    Message(
        id,

        senderId,

        senderName,

        text,

        createdAt,

        isRead
    )