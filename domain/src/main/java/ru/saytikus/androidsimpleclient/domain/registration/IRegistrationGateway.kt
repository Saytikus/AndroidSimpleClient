package ru.saytikus.androidsimpleclient.domain.registration

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.registration.answers.A1RegisterUserAnswer
import ru.saytikus.androidsimpleclient.domain.registration.commands.C1RegisterUserCommand

interface IRegistrationGateway {

    suspend fun registerUser(registerEvent: C1RegisterUserCommand): MbResult<A1RegisterUserAnswer>
}