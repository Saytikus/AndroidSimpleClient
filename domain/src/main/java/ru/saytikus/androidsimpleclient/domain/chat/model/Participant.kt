package ru.saytikus.androidsimpleclient.domain.chat.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class Participant(
    val userId: Uuid,
    val displayName: String,
    val username: String
)