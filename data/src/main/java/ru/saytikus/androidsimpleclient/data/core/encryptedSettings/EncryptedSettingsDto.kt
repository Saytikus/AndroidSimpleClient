package ru.saytikus.androidsimpleclient.data.core.encryptedSettings

import kotlinx.serialization.Serializable

@Serializable
data class EncryptedSettingsDto(
    val authenticationToken: String = "",

    val authenticationTokenExpiresAt: String = ""
)
