package ru.saytikus.androidsimpleclient.data.registration.dto

import kotlinx.serialization.Serializable

@Serializable
data class C1RegisterProfileCommandDto(
    val username: String,

    val email: String,

    val password: String,

    val displayName: String
)