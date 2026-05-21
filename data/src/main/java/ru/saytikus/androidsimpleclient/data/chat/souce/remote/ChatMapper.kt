@file:OptIn(ExperimentalUuidApi::class)

package ru.saytikus.androidsimpleclient.data.chat.souce.remote

import ru.saytikus.androidsimpleclient.data.chat.dto.ChatCreatedEventBodyDto
import ru.saytikus.androidsimpleclient.data.chat.dto.ChatDto
import ru.saytikus.androidsimpleclient.data.chat.dto.ChatListItemDto
import ru.saytikus.androidsimpleclient.data.chat.dto.CreatePrivateChatAnswerDto
import ru.saytikus.androidsimpleclient.data.chat.dto.ParticipantDto
import ru.saytikus.androidsimpleclient.data.core.message.source.remote.toDomain
import ru.saytikus.androidsimpleclient.domain.chat.dto.ChatCreatedEventBody
import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatAnswer
import ru.saytikus.androidsimpleclient.domain.chat.model.Chat
import ru.saytikus.androidsimpleclient.domain.chat.model.ChatListItem
import ru.saytikus.androidsimpleclient.domain.chat.model.Participant
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

@OptIn(ExperimentalUuidApi::class)
fun CreatePrivateChatAnswerDto.toDomain() =
    CreatePrivateChatAnswer(
        chatId
    )

fun ChatCreatedEventBodyDto.toDomain() =
    ChatCreatedEventBody(
        type,

        title,

        companionName,

        companionUsername
    )

fun ChatDto.toDomain() =
    Chat(
        chatId,

        participants.map { it.toDomain() },

        title ?: "NO_TITLE",

        type ?: "NO_TYPE"
    )

fun ParticipantDto.toDomain() =
    Participant(
        userId,
        displayName ?: "NO_DISPLAY_NAME",
        username ?: "NO_USERNAME"
    )