package ru.saytikus.androidsimpleclient.domain.authentication

import ru.saytikus.androidsimpleclient.domain.authentication.answers.A2SignInProfileAnswer
import ru.saytikus.androidsimpleclient.domain.authentication.commands.C2SignInProfileCommand
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult

interface IAuthenticationGateway {

    suspend fun requestSignInProfile(cmd: C2SignInProfileCommand): MbResult<A2SignInProfileAnswer>
}