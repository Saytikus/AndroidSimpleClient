package ru.saytikus.androidsimpleclient.domain.core.features.profileSearch

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.features.profileSearch.answers.ProfileSearchAnswer
import ru.saytikus.androidsimpleclient.domain.core.features.profileSearch.commands.ProfileSearchCommand

interface IProfileSearchGateway {

    suspend fun searchProfiles(searchCmd: ProfileSearchCommand): MbResult<ProfileSearchAnswer>
}