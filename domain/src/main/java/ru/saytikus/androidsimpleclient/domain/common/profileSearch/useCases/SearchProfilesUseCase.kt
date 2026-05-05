package ru.saytikus.androidsimpleclient.domain.common.profileSearch.useCases

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.profileSearch.IProfileSearchGateway
import ru.saytikus.androidsimpleclient.domain.common.profileSearch.answers.ProfileSearchAnswer
import ru.saytikus.androidsimpleclient.domain.common.profileSearch.commands.ProfileSearchCommand

class SearchProfilesUseCase(

    private val gateway: IProfileSearchGateway

) : IInputBoundary<MbResult<ProfileSearchAnswer>, ProfileSearchCommand> {

    override suspend fun invoke(cmd: ProfileSearchCommand): MbResult<ProfileSearchAnswer> {
        return gateway.searchProfiles(cmd)
    }
}