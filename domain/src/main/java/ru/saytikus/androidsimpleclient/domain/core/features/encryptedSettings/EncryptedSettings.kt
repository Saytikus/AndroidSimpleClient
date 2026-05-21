package ru.saytikus.androidsimpleclient.domain.core.features.encryptedSettings

data class EncryptedSettings(
    val authenticationToken: String,

    val authenticationTokenExpiresAt: String
)
