package ru.saytikus.androidsimpleclient.data.core.features.profile.gateway

import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.core.features.profile.source.remote.IProfileService
import ru.saytikus.androidsimpleclient.data.core.features.profile.source.remote.toDomain
import ru.saytikus.androidsimpleclient.data.core.handleRetrofitServiceResult
import ru.saytikus.androidsimpleclient.data.core.source.remote.retrofit.interfaces.IRetrofitProvider
import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.features.profile.IProfileGateway
import ru.saytikus.androidsimpleclient.domain.core.features.profile.model.ProfileId

@Single
class ProfileGateway(

    private val _retrofitProvider: IRetrofitProvider

) : IProfileGateway {

    private val _service: IProfileService
        get() = _retrofitProvider.retrofit().create(IProfileService::class.java)


    override suspend fun getProfileIdByUsernameOrEmail(usernameOrEmail: String): MbResult<ProfileId> {
        println("Gateway call ProfileGateway::getProfileIdByUsernameOrEmail")

        val result = runCatching { _service.getProfileIdByUsernameOrEmail(usernameOrEmail) }

        val answer = handleRetrofitServiceResult(result)

        return if (answer is MbResult.Success) MbResult.Success(answer.response.toDomain())
        else answer as MbResult.Failure
    }
}