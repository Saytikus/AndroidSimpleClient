package ru.saytikus.androidsimpleclient.data.authentication.gateway

import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.authentication.source.remote.IAuthenticationService
import ru.saytikus.androidsimpleclient.data.authentication.source.remote.toDomain
import ru.saytikus.androidsimpleclient.data.authentication.source.remote.toDto
import ru.saytikus.androidsimpleclient.data.core.handleRetrofitServiceResult
import ru.saytikus.androidsimpleclient.data.core.source.remote.retrofit.interfaces.IRetrofitProvider
import ru.saytikus.androidsimpleclient.domain.authentication.IAuthenticationGateway
import ru.saytikus.androidsimpleclient.domain.authentication.answers.A2SignInProfileAnswer
import ru.saytikus.androidsimpleclient.domain.authentication.commands.C2SignInProfileCommand
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult

@Single
class AuthenticationGateway(
    private val serviceProvider: IRetrofitProvider

) : IAuthenticationGateway  {

    private val _service: IAuthenticationService
        get() = serviceProvider.retrofit().create(IAuthenticationService::class.java)


    override suspend fun requestSignInProfile(cmd: C2SignInProfileCommand): MbResult<A2SignInProfileAnswer> {
        println("Gateway call AuthenticationGateway::requestSignInProfile")

        val result = runCatching { _service.requestSignInProfile(cmd.toDto()) }

        // TODO logger
        val answer = handleRetrofitServiceResult(result)

        return if(answer is MbResult.Success) MbResult.Success(answer.response.toDomain())
        else answer as MbResult.Failure
    }
}