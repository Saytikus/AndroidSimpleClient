package ru.saytikus.androidsimpleclient.domain.registration.answers

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class A1RegisterProfileAnswer @OptIn(ExperimentalUuidApi::class) constructor(
    val userId: Uuid,

    val username: String,

    val email: String,

    val displayName: String,

    val token: String,

    val expiresAt: String
)
