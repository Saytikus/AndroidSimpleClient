package ru.saytikus.androidsimpleclient.domain.registration.commands

data class C1RegisterUserCommand(
    val username: String,

    val email: String,

    val password: String,

    val displayName: String

)