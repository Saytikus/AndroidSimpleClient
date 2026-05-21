package ru.saytikus.androidsimpleclient.data.core.profile.source.remote

import ru.saytikus.androidsimpleclient.data.core.profile.source.remote.dto.ProfileIdDto
import ru.saytikus.androidsimpleclient.domain.core.profile.model.ProfileId
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
fun ProfileIdDto.toDomain() =
    ProfileId(
        userId
    )