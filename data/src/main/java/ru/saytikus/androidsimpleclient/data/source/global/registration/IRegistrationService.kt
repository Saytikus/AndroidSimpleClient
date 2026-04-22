package ru.saytikus.androidsimpleclient.data.source.global.registration

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.saytikus.androidsimpleclient.data.source.global.registration.dto.A1RegisterUserAnswerDto
import ru.saytikus.androidsimpleclient.data.source.global.registration.dto.C1RegisterUserCommandDto

interface IRegistrationService {

    @POST("/api/Auth/register")
    suspend fun registerUser(@Body registerUserCommand: C1RegisterUserCommandDto):
            Response<A1RegisterUserAnswerDto>
}