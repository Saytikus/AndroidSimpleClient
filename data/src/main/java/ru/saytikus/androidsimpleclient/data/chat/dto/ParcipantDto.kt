package ru.saytikus.androidsimpleclient.data.chat.dto

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Serializable
data class ParticipantDto(
    val userId: Uuid,
    val displayName: String?,
    val username: String?
)