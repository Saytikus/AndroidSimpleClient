package ru.saytikus.androidsimpleclient.domain.common.profile.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@JvmInline
value class ProfileId(
    val profileId: Uuid
)
