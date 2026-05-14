package ru.saytikus.androidsimpleclient.domain.chat.dto

data class ChatCreatedEventBody(
    val type: String,

    val title: String?,

    val companionName: String?,

    val companionUsername: String?
)