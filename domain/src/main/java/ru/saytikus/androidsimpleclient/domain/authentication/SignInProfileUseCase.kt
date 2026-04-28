package ru.saytikus.androidsimpleclient.domain.authentication

import ru.saytikus.androidsimpleclient.domain.authentication.answers.A2SignInProfileAnswer
import ru.saytikus.androidsimpleclient.domain.authentication.commands.C2SignInProfileCommand
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary

class SignInProfileUseCase(

    private val authGateway: IAuthenticationGateway

) : IInputBoundary<MbResult<A2SignInProfileAnswer>, C2SignInProfileCommand> {

    override suspend fun invoke(cmd: C2SignInProfileCommand): MbResult<A2SignInProfileAnswer> {
        return authGateway.requestSignInProfile(cmd)
    }
}