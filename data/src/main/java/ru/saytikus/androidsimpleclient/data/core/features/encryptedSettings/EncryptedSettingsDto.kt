package ru.saytikus.androidsimpleclient.data.core.features.encryptedSettings

import kotlinx.serialization.Serializable

@Serializable
data class EncryptedSettingsDto(
    val authenticationToken: String = "",

    val authenticationTokenExpiresAt: String = ""
)
