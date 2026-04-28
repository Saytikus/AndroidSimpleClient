package ru.saytikus.androidsimpleclient.data.authentication.dto

import kotlinx.serialization.Serializable

@Serializable
data class C2SignInProfileCommandDto(
    val usernameOrEmail: String,

    val password: String
)