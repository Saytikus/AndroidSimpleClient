package ru.saytikus.androidsimpleclient.domain.common.valueObject

import ru.saytikus.androidsimpleclient.domain.chat.model.ChatEvent

sealed interface DomainEvent {

    @JvmInline
    value class Chat(val event: ChatEvent) : DomainEvent
}