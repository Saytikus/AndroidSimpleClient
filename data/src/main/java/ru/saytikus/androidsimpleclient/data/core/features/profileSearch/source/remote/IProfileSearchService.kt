package ru.saytikus.androidsimpleclient.data.core.features.profileSearch.source.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.saytikus.androidsimpleclient.data.core.features.profileSearch.dto.ProfileSearchAnswerDto
import ru.saytikus.androidsimpleclient.data.core.source.remote.retrofit.RetrofitEndpoints

interface IProfileSearchService {

    @GET(RetrofitEndpoints.SEARCH)
    suspend fun searchProfiles(

        @Query("q") query: String,

        @Query("limit") limit: Int

    ): Response<ProfileSearchAnswerDto>
}