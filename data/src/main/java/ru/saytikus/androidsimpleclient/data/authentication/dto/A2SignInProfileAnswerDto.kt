package ru.saytikus.androidsimpleclient.data.authentication.dto

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class A2SignInProfileAnswerDto @OptIn(ExperimentalUuidApi::class) constructor(
    val userId: Uuid,

    val username: String,

    val email: String,

    val displayName: String,

    val token: String,

    val expiresAt: String
)