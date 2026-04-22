package ru.saytikus.androidsimpleclient.data.source.global.registration.dto

import kotlinx.serialization.Serializable

@Serializable
data class C1RegisterUserCommandDto(
    val login: String,

    val email: String,

    val password: String,

    val name: String
)