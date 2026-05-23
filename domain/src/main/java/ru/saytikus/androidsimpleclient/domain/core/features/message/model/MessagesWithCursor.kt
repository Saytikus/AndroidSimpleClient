package ru.saytikus.androidsimpleclient.domain.core.features.message.model

data class MessagesWithCursor(
    val items: List<Message>,

    val nextCursor: String,

    val hasMore: Boolean
)
