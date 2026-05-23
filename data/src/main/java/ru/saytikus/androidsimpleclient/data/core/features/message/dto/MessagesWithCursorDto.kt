package ru.saytikus.androidsimpleclient.data.core.features.message.dto

import kotlinx.serialization.Serializable

@Serializable
data class MessagesWithCursorDto(
    val items: List<MessageDto>,

    val nextCursor: String,

    val hasMore: Boolean
)