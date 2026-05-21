package ru.saytikus.androidsimpleclient.data.core.features.profile.source.remote.dto

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Serializable
data class ProfileIdDto(
    val userId: Uuid
)