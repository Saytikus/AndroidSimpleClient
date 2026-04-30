package ru.saytikus.androidsimpleclient.data.core.message

import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    val id: String,

    val senderId: String,

    val senderName: String,

    val text: String,

    val createdAt: String,

    val isRead: Boolean
)
