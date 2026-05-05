package ru.saytikus.androidsimpleclient.domain.chat.dto

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class CreatePrivateChatAnswer @OptIn(ExperimentalUuidApi::class) constructor(
    val chatId: Uuid
)