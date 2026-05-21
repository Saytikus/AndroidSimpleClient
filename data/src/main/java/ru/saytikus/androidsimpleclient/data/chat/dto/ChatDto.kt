package ru.saytikus.androidsimpleclient.data.chat.dto

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Serializable
data class ChatDto(
    val chatId: Uuid,
    val participants: List<ParticipantDto>,
    val title: String?,
    val type: String?
)