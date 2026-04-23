package ru.saytikus.androidsimpleclient.data.registration.source.remote

import ru.saytikus.androidsimpleclient.data.registration.dto.A1RegisterProfileAnswerDto
import ru.saytikus.androidsimpleclient.data.registration.dto.C1RegisterProfileCommandDto
import ru.saytikus.androidsimpleclient.domain.registration.answers.A1RegisterProfileAnswer
import ru.saytikus.androidsimpleclient.domain.registration.commands.C1RegisterProfileCommand
import kotlin.uuid.ExperimentalUuidApi

fun C1RegisterProfileCommand.toDto() =
    C1RegisterProfileCommandDto(
        username,

        email,

        password,

        displayName
    )

fun C1RegisterProfileCommandDto.toDomain() =
    C1RegisterProfileCommand(
        username,

        email,

        password,

        displayName
    )

@OptIn(ExperimentalUuidApi::class)
fun A1RegisterProfileAnswer.toDto() =
    A1RegisterProfileAnswerDto(
        userId,

        username,

        email,

        displayName,

        token,

        expiresAt
    )

@OptIn(ExperimentalUuidApi::class)
fun A1RegisterProfileAnswerDto.toDomain() =
    A1RegisterProfileAnswer(
        userId,

        username,

        email,

        displayName,

        token,

        expiresAt
    )