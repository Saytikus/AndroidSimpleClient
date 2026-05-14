package ru.saytikus.androidsimpleclient.domain.common.message.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Message @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid,

    val senderId: Uuid,

    val senderName: String?,

    val text: String,

    val createdAt: String,

    val isRead: Boolean
)