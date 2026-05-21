package ru.saytikus.androidsimpleclient.domain.core.message.model

sealed interface MessageEvent {

    data class MessageCreated(val message: Message) : MessageEvent
}