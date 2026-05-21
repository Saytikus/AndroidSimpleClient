package ru.saytikus.androidsimpleclient.data.core.features.profile.source.local

import ru.saytikus.androidsimpleclient.domain.core.features.profile.model.Profile
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun RoomProfile.toDomain() =
    Profile(
        Uuid.parse(userId),

        username,

        email,

        displayName
    )

@OptIn(ExperimentalUuidApi::class)
fun Profile.toRoom() =
    RoomProfile(
        userId.toString(),

        username,

        email,

        displayName
    )