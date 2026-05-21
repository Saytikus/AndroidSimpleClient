package ru.saytikus.androidsimpleclient.domain.core.profileSearch.useCases

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.core.profileSearch.IProfileSearchGateway
import ru.saytikus.androidsimpleclient.domain.core.profileSearch.answers.ProfileSearchAnswer
import ru.saytikus.androidsimpleclient.domain.core.profileSearch.commands.ProfileSearchCommand

class SearchProfilesUseCase(

    private val gateway: IProfileSearchGateway

) : IInputBoundary<MbResult<ProfileSearchAnswer>, ProfileSearchCommand> {

    override suspend fun invoke(cmd: ProfileSearchCommand): MbResult<ProfileSearchAnswer> {
        return gateway.searchProfiles(cmd)
    }
}