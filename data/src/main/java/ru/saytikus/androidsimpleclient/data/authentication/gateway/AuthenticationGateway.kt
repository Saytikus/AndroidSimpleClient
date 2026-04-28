package ru.saytikus.androidsimpleclient.data.authentication.gateway

import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.authentication.source.remote.IAuthenticationService
import ru.saytikus.androidsimpleclient.data.authentication.source.remote.toDomain
import ru.saytikus.androidsimpleclient.data.authentication.source.remote.toDto
import ru.saytikus.androidsimpleclient.data.core.source.remote.interfaces.IRetrofitProvider
import ru.saytikus.androidsimpleclient.domain.authentication.IAuthenticationGateway
import ru.saytikus.androidsimpleclient.domain.authentication.answers.A2SignInProfileAnswer
import ru.saytikus.androidsimpleclient.domain.authentication.commands.C2SignInProfileCommand
import ru.saytikus.androidsimpleclient.domain.common.dto.MbError
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.valueObject.DomainError

@Single
class AuthenticationGateway(
    private val serviceProvider: IRetrofitProvider

) : IAuthenticationGateway  {

    private val _service: IAuthenticationService
        get() = serviceProvider.retrofit().create(IAuthenticationService::class.java)


    override suspend fun requestSignInProfile(cmd: C2SignInProfileCommand): MbResult<A2SignInProfileAnswer> {
        // TODO try catch response
        val response = _service.requestSignInProfile(cmd.toDto())

        // TODO logger
        if(!response.isSuccessful) {

            println("SIGN IN USER ${cmd.usernameOrEmail} ERROR! CAUSE: ${response.code()} ${response.errorBody()?.string()}")

            return MbResult.Failure(
                MbError(
                    DomainError.GatewayError.RequestError(
                        response.code(),
                        response.errorBody()?.string()
                    )
                )
            )
        }

        println("SIGN IN USER ${cmd.usernameOrEmail} SUCCESSFULLY!")

        return MbResult.Success(response.body()?.toDomain()!!)
    }
}