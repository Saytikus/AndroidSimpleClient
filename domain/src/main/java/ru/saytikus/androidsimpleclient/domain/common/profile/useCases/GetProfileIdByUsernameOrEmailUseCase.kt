package ru.saytikus.androidsimpleclient.domain.common.profile.useCases

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.profile.IProfileGateway
import ru.saytikus.androidsimpleclient.domain.common.profile.model.ProfileId

class GetProfileIdByUsernameOrEmailUseCase(

    private val profileGateway: IProfileGateway

) : IInputBoundary<MbResult<ProfileId>, String> {

    override suspend fun invoke(cmd: String): MbResult<ProfileId> {
        return profileGateway.getProfileIdByUsernameOrEmail(cmd)
    }
}