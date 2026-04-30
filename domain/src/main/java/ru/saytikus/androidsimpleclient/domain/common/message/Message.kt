package ru.saytikus.androidsimpleclient.domain.common.message

data class Message(
    val id: String,

    val senderId: String,

    val senderName: String,

    val text: String,

    val createdAt: String,

    val isRead: Boolean
)