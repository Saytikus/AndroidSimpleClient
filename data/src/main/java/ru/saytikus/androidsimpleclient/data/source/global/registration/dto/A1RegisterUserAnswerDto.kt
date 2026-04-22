package ru.saytikus.androidsimpleclient.data.source.global.registration.dto

import kotlin.uuid.Uuid
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi

@Serializable
data class A1RegisterUserAnswerDto @OptIn(ExperimentalUuidApi::class) constructor(

    val userId: Uuid,

    val username: String,

    val email: String,

    val displayName: String,

    val registrationToken: String,

    val expiresAt: LocalDateTime
)