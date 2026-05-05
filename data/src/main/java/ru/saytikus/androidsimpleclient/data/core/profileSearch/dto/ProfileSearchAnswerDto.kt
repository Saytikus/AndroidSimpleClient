package ru.saytikus.androidsimpleclient.data.core.profileSearch.dto

import kotlinx.serialization.Serializable


@Serializable
data class ProfileSearchAnswerDto(

    val items: List<ProfileSearchListItemDto>,

    val query: String?,

    val totalCount: Int
)
