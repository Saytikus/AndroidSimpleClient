package ru.saytikus.androidsimpleclient.data.registration

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.saytikus.androidsimpleclient.data.registration.dto.A1RegisterProfileAnswerDto
import ru.saytikus.androidsimpleclient.data.registration.dto.C1RegisterProfileCommandDto

interface IRegistrationService {

    @POST("/api/Auth/register")
    suspend fun registerProfile(@Body registerProfileCommand: C1RegisterProfileCommandDto):
            Response<A1RegisterProfileAnswerDto>
}