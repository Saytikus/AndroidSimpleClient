package ru.saytikus.androidsimpleclient.domain.core.profile

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.profile.model.ProfileId

interface IProfileGateway {

    suspend fun getProfileIdByUsernameOrEmail(usernameOrEmail: String): MbResult<ProfileId>
}