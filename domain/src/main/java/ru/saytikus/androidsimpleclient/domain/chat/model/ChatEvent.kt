@file:OptIn(ExperimentalUuidApi::class)

package ru.saytikus.androidsimpleclient.domain.chat.model

import ru.saytikus.androidsimpleclient.domain.chat.dto.ChatCreatedEventBody
import ru.saytikus.androidsimpleclient.domain.common.message.model.Message
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

sealed interface ChatEvent {

    data class ChatListUpdatedEvent(
        val chatId: Uuid,

        val message: Message

    ) : ChatEvent

    data class ChatCreatedEvent(
        val chatId: Uuid,

        val body: ChatCreatedEventBody

    ) : ChatEvent

    data class UserOnlineChangedEvent(
        val chatId: Uuid,

        val isOnline: Boolean

    ) : ChatEvent

    data class TypingChangedEvent(
        val chatId: Uuid,

        val userId: Uuid,

        val isOnline: Boolean

    ) : ChatEvent
}