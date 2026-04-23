package ru.saytikus.androidsimpleclient.domain.registration

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.registration.answers.A1RegisterProfileAnswer
import ru.saytikus.androidsimpleclient.domain.registration.commands.C1RegisterProfileCommand

interface IRegistrationGateway {

    suspend fun registerProfile(registerEvent: C1RegisterProfileCommand): MbResult<A1RegisterProfileAnswer>
}