package ru.saytikus.androidsimpleclient.data.core.message

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class MessageDto @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid,

    val senderId: Uuid,

    val senderName: String?,

    val text: String,

    val createdAt: String,

    val isRead: Boolean
)
