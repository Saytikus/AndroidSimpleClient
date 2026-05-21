package ru.saytikus.androidsimpleclient.data.core.features.encryptedSettings

import ru.saytikus.androidsimpleclient.domain.core.features.encryptedSettings.EncryptedSettings

fun EncryptedSettings.toDto() =
    EncryptedSettingsDto(
        authenticationToken = authenticationToken,

        authenticationTokenExpiresAt = authenticationTokenExpiresAt
    )


fun EncryptedSettingsDto.toDomain() =
    EncryptedSettings(
        authenticationToken = authenticationToken,

        authenticationTokenExpiresAt = authenticationTokenExpiresAt
    )