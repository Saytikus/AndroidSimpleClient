package ru.saytikus.androidsimpleclient.data.core.features.profile.source.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.saytikus.androidsimpleclient.data.core.features.profile.source.remote.dto.ProfileIdDto
import ru.saytikus.androidsimpleclient.data.core.source.remote.retrofit.RetrofitEndpoints

interface IProfileService {

    @GET(RetrofitEndpoints.GET_USER_ID)
    suspend fun getProfileIdByUsernameOrEmail(@Path("usernameOrEmail") usernameOrEmail: String): Response<ProfileIdDto>
}