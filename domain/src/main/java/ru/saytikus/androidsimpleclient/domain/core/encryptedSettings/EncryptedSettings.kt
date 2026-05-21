package ru.saytikus.androidsimpleclient.domain.core.encryptedSettings

data class EncryptedSettings(
    val authenticationToken: String,

    val authenticationTokenExpiresAt: String
)
