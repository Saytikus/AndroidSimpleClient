package ru.saytikus.androidsimpleclient.data.authentication.source.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.saytikus.androidsimpleclient.data.authentication.dto.A2SignInProfileAnswerDto
import ru.saytikus.androidsimpleclient.data.authentication.dto.C2SignInProfileCommandDto

interface IAuthenticationService {

    @POST("/api/Auth/login")
    suspend fun requestSignInProfile(@Body cmd: C2SignInProfileCommandDto): Response<A2SignInProfileAnswerDto>
}