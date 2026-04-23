package ru.saytikus.androidsimpleclient.data.core.profile.source.local

import ru.saytikus.androidsimpleclient.domain.common.entities.Profile
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