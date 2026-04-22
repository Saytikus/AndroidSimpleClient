package ru.saytikus.androidsimpleclient.data.source.global.registration

import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.source.global.common.interfaces.IRetrofitProvider
import ru.saytikus.androidsimpleclient.domain.common.dto.MbError
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.valueObject.DomainError
import ru.saytikus.androidsimpleclient.domain.registration.IRegistrationGateway
import ru.saytikus.androidsimpleclient.domain.registration.answers.A1RegisterUserAnswer
import ru.saytikus.androidsimpleclient.domain.registration.commands.C1RegisterUserCommand


@Single
class RegistrationGateway(

    private val serviceProvider: IRetrofitProvider

) : IRegistrationGateway {


    private val _service: IRegistrationService
        get() = serviceProvider.retrofit().create(IRegistrationService::class.java)



    override suspend fun registerUser(registerEvent: C1RegisterUserCommand): MbResult<A1RegisterUserAnswer> {
        val response = _service.registerUser(registerEvent.toDto())

        if(!response.isSuccessful) {
            return MbResult.Failure(
                MbError(
                    DomainError.GatewayError.RequestError(
                        response.code(),
                        response.errorBody().toString()
                    )
                )
            )
        }

        return MbResult.Success(
            response.body()?.toDomain()!!
        )
    }
}