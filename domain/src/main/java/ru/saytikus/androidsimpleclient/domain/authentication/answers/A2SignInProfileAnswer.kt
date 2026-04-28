package ru.saytikus.androidsimpleclient.domain.authentication.answers

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class A2SignInProfileAnswer @OptIn(ExperimentalUuidApi::class) constructor(
    val userId: Uuid,

    val username: String,

    val email: String,

    val displayName: String,

    val token: String,

    val expiresAt: String
)
