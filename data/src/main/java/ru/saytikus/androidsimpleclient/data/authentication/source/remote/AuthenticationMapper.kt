package ru.saytikus.androidsimpleclient.data.authentication.source.remote

import ru.saytikus.androidsimpleclient.data.authentication.dto.A2SignInProfileAnswerDto
import ru.saytikus.androidsimpleclient.data.authentication.dto.C2SignInProfileCommandDto
import ru.saytikus.androidsimpleclient.domain.authentication.answers.A2SignInProfileAnswer
import ru.saytikus.androidsimpleclient.domain.authentication.commands.C2SignInProfileCommand
import kotlin.uuid.ExperimentalUuidApi

fun C2SignInProfileCommand.toDto() =

    C2SignInProfileCommandDto(

        usernameOrEmail,

        password
    )


@OptIn(ExperimentalUuidApi::class)
fun A2SignInProfileAnswerDto.toDomain() =

    A2SignInProfileAnswer(
        userId,

        username,

        email,

        displayName,

        token,

        displayName
    )