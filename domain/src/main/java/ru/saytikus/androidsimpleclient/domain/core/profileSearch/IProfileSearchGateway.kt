package ru.saytikus.androidsimpleclient.domain.core.profileSearch

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.profileSearch.answers.ProfileSearchAnswer
import ru.saytikus.androidsimpleclient.domain.core.profileSearch.commands.ProfileSearchCommand

interface IProfileSearchGateway {

    suspend fun searchProfiles(searchCmd: ProfileSearchCommand): MbResult<ProfileSearchAnswer>
}