package ru.saytikus.androidsimpleclient.data.authentication.source.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.saytikus.androidsimpleclient.data.authentication.dto.A2SignInProfileAnswerDto
import ru.saytikus.androidsimpleclient.data.authentication.dto.C2SignInProfileCommandDto
import ru.saytikus.androidsimpleclient.domain.authentication.answers.A2SignInProfileAnswer
import ru.saytikus.androidsimpleclient.domain.authentication.commands.C2SignInProfileCommand

interface IAuthenticationService {

    @POST("/api/Auth/login")
    suspend fun requestSignInProfile(@Body cmd: C2SignInProfileCommandDto): Response<A2SignInProfileAnswerDto>
}