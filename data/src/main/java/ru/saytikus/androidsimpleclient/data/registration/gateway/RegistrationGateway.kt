package ru.saytikus.androidsimpleclient.data.registration.gateway

import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.core.source.remote.interfaces.IRetrofitProvider
import ru.saytikus.androidsimpleclient.data.registration.source.remote.IRegistrationService
import ru.saytikus.androidsimpleclient.data.registration.source.remote.toDomain
import ru.saytikus.androidsimpleclient.data.registration.source.remote.toDto
import ru.saytikus.androidsimpleclient.domain.common.dto.MbError
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.valueObject.DomainError
import ru.saytikus.androidsimpleclient.domain.registration.IRegistrationGateway
import ru.saytikus.androidsimpleclient.domain.registration.answers.A1RegisterProfileAnswer
import ru.saytikus.androidsimpleclient.domain.registration.commands.C1RegisterProfileCommand

@Single
class RegistrationGateway(

    private val serviceProvider: IRetrofitProvider

) : IRegistrationGateway {


    private val _service: IRegistrationService
        get() = serviceProvider.retrofit().create(IRegistrationService::class.java)



    override suspend fun registerProfile(registerEvent: C1RegisterProfileCommand): MbResult<A1RegisterProfileAnswer> {
        val response = _service.registerProfile(registerEvent.toDto())

        if(!response.isSuccessful) {
            return MbResult.Failure(
                MbError(
                    DomainError.GatewayError.RequestError(
                        response.code(),
                        response.errorBody()?.string()
                    )
                )
            )
        }

        println("RegistrationGateway::registerProfile: receive answer on C1RegisterProfileCommand: ${response.body()}")

        // todo fix !!
        return MbResult.Success(
            response.body()?.toDomain()!!
        )
    }
}