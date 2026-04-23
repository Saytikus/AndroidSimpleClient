package ru.saytikus.androidsimpleclient.domain.common.entities

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Profile @OptIn(ExperimentalUuidApi::class) constructor(
    val userId: Uuid,

    val username: String,

    val email: String,

    val displayName: String,
)
