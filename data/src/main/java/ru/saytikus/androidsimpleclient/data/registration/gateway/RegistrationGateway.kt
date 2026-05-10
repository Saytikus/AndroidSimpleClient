package ru.saytikus.androidsimpleclient.data.registration.gateway

import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.core.handleRetrofitServiceResult
import ru.saytikus.androidsimpleclient.data.core.source.remote.retrofit.interfaces.IRetrofitProvider
import ru.saytikus.androidsimpleclient.data.registration.source.remote.IRegistrationService
import ru.saytikus.androidsimpleclient.data.registration.source.remote.toDomain
import ru.saytikus.androidsimpleclient.data.registration.source.remote.toDto
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
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
        println("Gateway call RegistrationGateway::registerProfile")

        val result = runCatching { _service.registerProfile(registerEvent.toDto()) }

        val answer = handleRetrofitServiceResult(result)

        return if(answer is MbResult.Success) MbResult.Success(answer.response.toDomain())
        else answer as MbResult.Failure
    }
}