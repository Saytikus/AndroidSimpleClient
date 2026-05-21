package ru.saytikus.androidsimpleclient.domain.common.profile

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.profile.model.ProfileId

interface IProfileGateway {

    suspend fun getProfileIdByUsernameOrEmail(usernameOrEmail: String): MbResult<ProfileId>
}