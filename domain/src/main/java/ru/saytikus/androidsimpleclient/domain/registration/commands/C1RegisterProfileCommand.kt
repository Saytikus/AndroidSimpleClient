package ru.saytikus.androidsimpleclient.domain.registration.commands

data class C1RegisterProfileCommand(
    val username: String,

    val email: String,

    val password: String,

    val displayName: String

)