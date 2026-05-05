package ru.saytikus.androidsimpleclient.data.chat.dto

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@Serializable
data class CreatePrivateChatAnswerDto @OptIn(ExperimentalUuidApi::class) constructor(
    val chatId: Uuid
)
