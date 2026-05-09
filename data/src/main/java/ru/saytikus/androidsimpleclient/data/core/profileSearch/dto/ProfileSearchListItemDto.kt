package ru.saytikus.androidsimpleclient.data.core.profileSearch.dto

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@OptIn(ExperimentalUuidApi::class)
@Serializable
data class ProfileSearchListItemDto(

    val userId: Uuid,

    val username: String?,

    val displayName: String?,

    val avatarUrl: String?
)
