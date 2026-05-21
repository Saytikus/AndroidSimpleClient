package ru.saytikus.androidsimpleclient.data.core.profileSearch

import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.core.handleRetrofitServiceResult
import ru.saytikus.androidsimpleclient.data.core.profileSearch.source.remote.IProfileSearchService
import ru.saytikus.androidsimpleclient.data.core.profileSearch.source.remote.toDomain
import ru.saytikus.androidsimpleclient.data.core.source.remote.retrofit.RetrofitProvider
import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.features.profileSearch.IProfileSearchGateway
import ru.saytikus.androidsimpleclient.domain.core.features.profileSearch.answers.ProfileSearchAnswer
import ru.saytikus.androidsimpleclient.domain.core.features.profileSearch.commands.ProfileSearchCommand


@Single
class ProfileSearchGateway(

    private val _retrofitProvider: RetrofitProvider

) : IProfileSearchGateway {

    private val _service: IProfileSearchService
        get() = _retrofitProvider.retrofit().create(IProfileSearchService::class.java)


    override suspend fun searchProfiles(searchCmd: ProfileSearchCommand): MbResult<ProfileSearchAnswer> {
        println("Gateway call ProfileSearchGateway::searchProfiles")

        val result = runCatching {
            _service.searchProfiles(
                searchCmd.q,
                searchCmd.limit
            )
        }

        val answer = handleRetrofitServiceResult(result)

        return if (answer is MbResult.Success) MbResult.Success(answer.response.toDomain())
        else answer as MbResult.Failure
    }
}