package ru.saytikus.androidsimpleclient.domain.common.profileSearch.answers

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class ProfileSearchListItem @OptIn(ExperimentalUuidApi::class) constructor(

    val userId: Uuid,

    val username: String?,

    val displayName: String?,

    val avatarUrl: String?
)