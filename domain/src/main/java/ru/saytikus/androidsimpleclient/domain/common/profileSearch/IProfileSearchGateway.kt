package ru.saytikus.androidsimpleclient.domain.common.profileSearch

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.profileSearch.answers.ProfileSearchAnswer
import ru.saytikus.androidsimpleclient.domain.common.profileSearch.commands.ProfileSearchCommand

interface IProfileSearchGateway {

    suspend fun searchProfiles(searchCmd: ProfileSearchCommand): MbResult<ProfileSearchAnswer>
}