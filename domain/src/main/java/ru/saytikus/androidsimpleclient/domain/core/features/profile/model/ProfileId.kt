package ru.saytikus.androidsimpleclient.domain.core.features.profile.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@JvmInline
value class ProfileId(
    val profileId: Uuid
)
