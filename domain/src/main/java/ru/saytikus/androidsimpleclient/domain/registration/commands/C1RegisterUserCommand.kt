package ru.saytikus.androidsimpleclient.domain.registration.commands

data class C1RegisterUserCommand(
    val login: String,

    val email: String,

    val password: String,

    val name: String

)