package ru.saytikus.androidsimpleclient.domain.common.encryptedSettings

data class EncryptedSettings(
    val authenticationToken: String,

    val authenticationTokenExpiresAt: String
)
