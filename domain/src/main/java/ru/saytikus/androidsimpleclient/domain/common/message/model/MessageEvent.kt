package ru.saytikus.androidsimpleclient.domain.common.message.model

sealed interface MessageEvent {

    data class MessageCreated(val message: Message) : MessageEvent
}