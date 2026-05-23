package ru.saytikus.androidsimpleclient.domain.core.features.message.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class GetMessagesWithCursorCommand(
    val chatId: Uuid,

    val cursor: String? = null,

    val limit: Int
)
