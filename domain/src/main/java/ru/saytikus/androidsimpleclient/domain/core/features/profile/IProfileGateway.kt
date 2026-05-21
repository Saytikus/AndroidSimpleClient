package ru.saytikus.androidsimpleclient.domain.core.features.profile

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.features.profile.model.ProfileId

interface IProfileGateway {

    suspend fun getProfileIdByUsernameOrEmail(usernameOrEmail: String): MbResult<ProfileId>
}