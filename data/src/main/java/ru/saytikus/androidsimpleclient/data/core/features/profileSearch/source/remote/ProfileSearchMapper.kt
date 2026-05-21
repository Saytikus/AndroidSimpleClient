package ru.saytikus.androidsimpleclient.data.core.features.profileSearch.source.remote

import ru.saytikus.androidsimpleclient.data.core.features.profileSearch.dto.ProfileSearchAnswerDto
import ru.saytikus.androidsimpleclient.data.core.features.profileSearch.dto.ProfileSearchListItemDto
import ru.saytikus.androidsimpleclient.domain.core.features.profileSearch.answers.ProfileSearchAnswer
import ru.saytikus.androidsimpleclient.domain.core.features.profileSearch.answers.ProfileSearchListItem
import kotlin.uuid.ExperimentalUuidApi


@OptIn(ExperimentalUuidApi::class)
fun ProfileSearchListItemDto.toDomain() =
    ProfileSearchListItem(

        userId,

        username,

        displayName,

        avatarUrl
    )

fun ProfileSearchAnswerDto.toDomain() =
    ProfileSearchAnswer(

        items.map { it.toDomain() },

        query,

        totalCount

    )