package ru.saytikus.androidsimpleclient.data.registration.dto

import kotlin.uuid.Uuid
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi

@Serializable
data class A1RegisterUserAnswerDto @OptIn(ExperimentalUuidApi::class) constructor(

    val userId: Uuid,

    val username: String,

    val email: String,

    val displayName: String,

    val token: String,

    val expiresAt: String
)