package ru.saytikus.androidsimpleclient.domain.registration.useCases

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.registration.IRegistrationGateway
import ru.saytikus.androidsimpleclient.domain.registration.answers.A1RegisterUserAnswer
import ru.saytikus.androidsimpleclient.domain.registration.commands.C1RegisterUserCommand

class RegisterUserUseCase(

    private val registrationGateway: IRegistrationGateway

) : IInputBoundary<MbResult<A1RegisterUserAnswer>, C1RegisterUserCommand> {

    override suspend fun invoke(cmd: C1RegisterUserCommand): MbResult<A1RegisterUserAnswer> {
        val registerResult = registrationGateway.registerUser(cmd)

        // TODO logger
        when (registerResult) {
            is MbResult.Failure -> {
                println("Fail user register. Cause: ${registerResult.error}")
            }

            is MbResult.Success<A1RegisterUserAnswer> -> {
                println("User registered successfully")
                println(registerResult.response)
            }
        }

        return registerResult
    }
}