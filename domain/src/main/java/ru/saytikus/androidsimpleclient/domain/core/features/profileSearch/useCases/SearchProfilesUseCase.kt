package ru.saytikus.androidsimpleclient.domain.core.features.profileSearch.useCases

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.features.profileSearch.IProfileSearchGateway
import ru.saytikus.androidsimpleclient.domain.core.features.profileSearch.answers.ProfileSearchAnswer
import ru.saytikus.androidsimpleclient.domain.core.features.profileSearch.commands.ProfileSearchCommand
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary

class SearchProfilesUseCase(

    private val gateway: IProfileSearchGateway

) : IInputBoundary<MbResult<ProfileSearchAnswer>, ProfileSearchCommand> {

    override suspend fun invoke(cmd: ProfileSearchCommand): MbResult<ProfileSearchAnswer> {
        return gateway.searchProfiles(cmd)
    }
}