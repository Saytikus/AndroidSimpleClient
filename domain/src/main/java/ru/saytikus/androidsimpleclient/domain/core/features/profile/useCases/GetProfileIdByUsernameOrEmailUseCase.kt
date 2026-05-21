package ru.saytikus.androidsimpleclient.domain.core.features.profile.useCases

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.features.profile.IProfileGateway
import ru.saytikus.androidsimpleclient.domain.core.features.profile.model.ProfileId
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary

class GetProfileIdByUsernameOrEmailUseCase(

    private val profileGateway: IProfileGateway

) : IInputBoundary<MbResult<ProfileId>, String> {

    override suspend fun invoke(cmd: String): MbResult<ProfileId> {
        return profileGateway.getProfileIdByUsernameOrEmail(cmd)
    }
}