package ru.saytikus.androidsimpleclient.data.chat.dto

import kotlinx.serialization.Serializable

@Serializable
data class ChatCreatedEventBodyDto(
    val type: String,

    val title: String?,

    val companionName: String?,

    val companionUsername: String?
)