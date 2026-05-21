package ru.saytikus.androidsimpleclient.domain.core.profile.useCases

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.core.profile.IProfileGateway
import ru.saytikus.androidsimpleclient.domain.core.profile.model.ProfileId

class GetProfileIdByUsernameOrEmailUseCase(

    private val profileGateway: IProfileGateway

) : IInputBoundary<MbResult<ProfileId>, String> {

    override suspend fun invoke(cmd: String): MbResult<ProfileId> {
        return profileGateway.getProfileIdByUsernameOrEmail(cmd)
    }
}