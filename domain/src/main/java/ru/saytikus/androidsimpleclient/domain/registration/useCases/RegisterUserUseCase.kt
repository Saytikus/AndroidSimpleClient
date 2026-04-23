package ru.saytikus.androidsimpleclient.domain.registration.useCases

import ru.saytikus.androidsimpleclient.domain.common.dto.MbError
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.valueObject.DomainError
import ru.saytikus.androidsimpleclient.domain.registration.IRegistrationGateway
import ru.saytikus.androidsimpleclient.domain.registration.answers.A1RegisterUserAnswer
import ru.saytikus.androidsimpleclient.domain.registration.commands.C1RegisterUserCommand
import kotlin.uuid.ExperimentalUuidApi

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

                val validateResult = validateRegisterAnswer(cmd, registerResult.response)

                if(validateResult.first) {
                    println("User registered successfully")
                    println(registerResult.response)
                } else {
                    println(validateResult.second)
                    return MbResult.Failure(
                        MbError(
                            DomainError.DomainValidateError.IncorrectAnswerData(validateResult.second)
                        )
                    )
                }
            }
        }

        return registerResult
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun validateRegisterAnswer(
        cmd: C1RegisterUserCommand,
        answer: A1RegisterUserAnswer
    ): Pair<Boolean, String> {

        var resultString = "Fail user register. Cause: "
        if(cmd.username != answer.username) {
            resultString += "Requested username isn't equal username in answer."
        } else if(cmd.email != answer.email) {
            resultString += "Requested email isn't equal email in answer."
        } else if(cmd.displayName != answer.displayName) {
            resultString += "Requested display name isn't equal display name in answer."

        } else if(answer.userId.toString().isEmpty()) {
            resultString += "User uuid in answer is empty."
        } else if(answer.token.isEmpty()) {
            resultString += "registration token in answer is empty."
        } else if(answer.expiresAt.isEmpty()) {
            resultString += "expires at in answer is empty."
        }

        return if(resultString == "Fail user register. Cause: ") Pair(true, "")
               else Pair(false, resultString)
    }
}