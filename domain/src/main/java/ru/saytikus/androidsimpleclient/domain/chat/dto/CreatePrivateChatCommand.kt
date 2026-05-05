package ru.saytikus.androidsimpleclient.domain.chat.dto

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class CreatePrivateChatCommand @OptIn(ExperimentalUuidApi::class) constructor(
    val selectedUserId: Uuid
)