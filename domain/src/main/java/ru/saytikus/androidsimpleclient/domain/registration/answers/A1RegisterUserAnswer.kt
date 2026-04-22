package ru.saytikus.androidsimpleclient.domain.registration.answers

import kotlinx.datetime.LocalDateTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class A1RegisterUserAnswer @OptIn(ExperimentalUuidApi::class) constructor(
    val userId: Uuid,

    val username: String,

    val email: String,

    val displayName: String,

    val registrationToken: String,

    val expiresAt: LocalDateTime
)
