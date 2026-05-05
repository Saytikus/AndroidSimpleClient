package ru.saytikus.androidsimpleclient.data.core.profileSearch.dto

import kotlinx.serialization.Serializable


@Serializable
data class ProfileSearchListItemDto(

    val userId: String?,

    val username: String?,

    val displayName: String?,

    val avatarUrl: String?
)
