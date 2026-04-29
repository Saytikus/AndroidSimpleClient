package ru.saytikus.androidsimpleclient.data.core.encryptedSettings

import ru.saytikus.androidsimpleclient.domain.common.encryptedSettings.EncryptedSettings

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