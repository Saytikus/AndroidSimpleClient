package ru.saytikus.androidsimpleclient.domain.authentication.commands

data class C2SignInProfileCommand(
    val usernameOrEmail: String,

    val password: String
)
