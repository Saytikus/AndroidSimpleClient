package ru.saytikus.androidsimpleclient.domain.common.valueObject

import ru.saytikus.androidsimpleclient.domain.chat.model.ChatEvent
import ru.saytikus.androidsimpleclient.domain.common.message.model.MessageEvent

sealed interface DomainEvent {

    @JvmInline
    value class Chat(val event: ChatEvent) : DomainEvent

    @JvmInline
    value class Message(val event: MessageEvent) : DomainEvent
}