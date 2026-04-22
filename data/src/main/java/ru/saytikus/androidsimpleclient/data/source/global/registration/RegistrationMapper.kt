package ru.saytikus.androidsimpleclient.data.source.global.registration

import ru.saytikus.androidsimpleclient.data.source.global.registration.dto.A1RegisterUserAnswerDto
import ru.saytikus.androidsimpleclient.data.source.global.registration.dto.C1RegisterUserCommandDto
import ru.saytikus.androidsimpleclient.domain.registration.answers.A1RegisterUserAnswer
import ru.saytikus.androidsimpleclient.domain.registration.commands.C1RegisterUserCommand
import kotlin.uuid.ExperimentalUuidApi

fun C1RegisterUserCommand.toDto() =
    C1RegisterUserCommandDto(
        login,

        email,

        password,

        name
    )

fun C1RegisterUserCommandDto.toDomain() =
    C1RegisterUserCommand(
        login,

        email,

        password,

        name
    )

@OptIn(ExperimentalUuidApi::class)
fun A1RegisterUserAnswer.toDto() =
    A1RegisterUserAnswerDto(
        userId,

        username,

        email,

        displayName,

        registrationToken,

        expiresAt
    )

@OptIn(ExperimentalUuidApi::class)
fun A1RegisterUserAnswerDto.toDomain() =
    A1RegisterUserAnswer(
        userId,

        username,

        email,

        displayName,

        registrationToken,

        expiresAt
    )