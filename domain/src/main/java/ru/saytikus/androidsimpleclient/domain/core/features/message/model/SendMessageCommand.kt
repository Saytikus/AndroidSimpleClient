package ru.saytikus.androidsimpleclient.domain.core.features.message.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class SendMessageCommand(
    val chatId: Uuid,

    val text: String
)