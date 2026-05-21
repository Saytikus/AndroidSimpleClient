package ru.saytikus.androidsimpleclient.domain.chat.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class Chat(
    val chatId: Uuid,
    val participants: List<Participant>,
    val title: String,
    val type: String
)